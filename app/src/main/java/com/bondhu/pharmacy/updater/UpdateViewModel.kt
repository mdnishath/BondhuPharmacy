package com.bondhu.pharmacy.updater

import android.app.Application
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class UpdateViewModel(application: Application) : AndroidViewModel(application) {

    private val updater = InAppUpdater(application)
    
    // We can get the version from context packaging
    private val currentVersion = try {
        application.packageManager.getPackageInfo(application.packageName, 0).versionName ?: "1.0.0"
    } catch (e: Exception) {
        "1.0.0"
    }

    private val _updateInfo = MutableStateFlow<UpdateInfo?>(null)
    val updateInfo: StateFlow<UpdateInfo?> = _updateInfo.asStateFlow()

    private val _isDownloading = MutableStateFlow(false)
    val isDownloading: StateFlow<Boolean> = _isDownloading.asStateFlow()

    private var downloadId: Long = -1L
    private var downloadedApkVersion = ""

    private val downloadReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id == downloadId && context != null) {
                _isDownloading.value = false
                // Trigger installation
                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "BondhuPharmacy_v$downloadedApkVersion.apk"
                )
                updater.installApk(file)
            }
        }
    }

    init {
        // Register receiver for when download finishes
        // Note: For Android 13+ you might need RECEIVER_EXPORTED or RECEIVER_NOT_EXPORTED
        application.registerReceiver(
            downloadReceiver, 
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            Context.RECEIVER_EXPORTED
        )
        checkForUpdates()
    }

    fun checkForUpdates() {
        viewModelScope.launch {
            val info = updater.checkForUpdates(currentVersion)
            if (info != null && info.hasUpdate) {
                _updateInfo.value = info
            }
        }
    }

    fun startDownload() {
        val info = _updateInfo.value ?: return
        downloadedApkVersion = info.latestVersion
        _isDownloading.value = true
        downloadId = updater.downloadAndInstallUpdate(info.downloadUrl, info.latestVersion)
    }

    fun dismissUpdate() {
        _updateInfo.value = null
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<Application>().unregisterReceiver(downloadReceiver)
    }
}
