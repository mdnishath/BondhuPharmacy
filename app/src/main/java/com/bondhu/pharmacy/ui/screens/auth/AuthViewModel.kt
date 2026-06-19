package com.bondhu.pharmacy.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bondhu.pharmacy.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _authError = MutableStateFlow<String?>(null)
    val authError: StateFlow<String?> = _authError.asStateFlow()

    private val _authSuccess = MutableStateFlow(false)
    val authSuccess: StateFlow<Boolean> = _authSuccess.asStateFlow()

    fun updateEmail(newEmail: String) { _email.value = newEmail }
    fun updatePassword(newPass: String) { _password.value = newPass }

    fun login() {
        if (_email.value.isBlank() || _password.value.isBlank()) {
            _authError.value = "ইমেইল বা পাসওয়ার্ড খালি রাখা যাবে না"
            return
        }
        _isLoading.value = true
        _authError.value = null
        viewModelScope.launch {
            val result = AuthRepository.login(_email.value, _password.value)
            _isLoading.value = false
            if (result.isSuccess) {
                _authSuccess.value = true
            } else {
                _authError.value = result.exceptionOrNull()?.message ?: "Login failed"
            }
        }
    }

    fun signup() {
        if (_email.value.isBlank() || _password.value.isBlank()) {
            _authError.value = "ইমেইল বা পাসওয়ার্ড খালি রাখা যাবে না"
            return
        }
        if (_password.value.length < 6) {
            _authError.value = "পাসওয়ার্ড অন্তত ৬ ক্যারেক্টার হতে হবে"
            return
        }
        _isLoading.value = true
        _authError.value = null
        viewModelScope.launch {
            val result = AuthRepository.signup(_email.value, _password.value)
            _isLoading.value = false
            if (result.isSuccess) {
                _authSuccess.value = true
            } else {
                _authError.value = result.exceptionOrNull()?.message ?: "Signup failed"
            }
        }
    }
    
    fun resetState() {
        _authError.value = null
        _authSuccess.value = false
    }
}
