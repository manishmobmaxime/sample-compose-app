package com.compose.app.presentation.ui.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.compose.app.data.SettingsRepository
import com.compose.app.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val settings: SettingsRepository,
) : ScreenModel {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onUsernameChange(username: String) {
        _state.value = _state.value.copy(username = username)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun login() {
        screenModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error =  null)
            try {
                val result = authRepository.login(
                    _state.value.username,
                    _state.value.password
                )

                if(result?.isSuccess == true) {
                    _state.value = _state.value.copy(
                        isLoading =  false,
                        success = true
                    )
                    settings.setAuthToken(result.data?.token)
                } else {
                    _state.value = _state.value.copy(
                        isLoading =  false,
                        error = "Invalid credentials"
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Login Failed: ${e.message}"
                )
            }
        }
    }

    fun loginSuccess() {
        _state.value = state.value.copy(success = false)
    }
}