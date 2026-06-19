package com.bondhu.pharmacy.updater

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bondhu.pharmacy.ui.theme.LimeGreen
import com.bondhu.pharmacy.ui.theme.PanelDark

@Composable
fun UpdateDialog(
    viewModel: UpdateViewModel = viewModel()
) {
    val updateInfo by viewModel.updateInfo.collectAsState()
    val isDownloading by viewModel.isDownloading.collectAsState()

    updateInfo?.let { info ->
        AlertDialog(
            onDismissRequest = { 
                // Do not allow dismiss if forcing update, but for now we let them dismiss
                if (!isDownloading) viewModel.dismissUpdate() 
            },
            containerColor = PanelDark,
            titleContentColor = LimeGreen,
            textContentColor = MaterialTheme.colorScheme.onSurface,
            title = {
                Text("New Update Available!", fontWeight = FontWeight.Bold)
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Version ${info.latestVersion} is now available.",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "What's new:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = LimeGreen
                    )
                    Text(
                        text = info.releaseNotes,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    if (isDownloading) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator(
                            color = LimeGreen,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Downloading...", color = LimeGreen, style = MaterialTheme.typography.bodySmall)
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.startDownload() },
                    colors = ButtonDefaults.buttonColors(containerColor = LimeGreen),
                    enabled = !isDownloading
                ) {
                    Text(if (isDownloading) "Downloading..." else "Update Now", color = PanelDark)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { viewModel.dismissUpdate() },
                    enabled = !isDownloading
                ) {
                    Text("Later", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        )
    }
}
