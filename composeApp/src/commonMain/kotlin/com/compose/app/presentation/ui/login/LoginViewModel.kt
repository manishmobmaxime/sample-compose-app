package com.compose.kotlin.presentation.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.compose.kotlin.data.repository.AuthRepository
import com.compose.kotlin.presentation.navigation.AppScreen
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val navigator: Navigator
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onUsernameChange(username: String) {
        _state.value = _state.value.copy(username = username)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun login() = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true, error =  null)
        try {
            val success = authRepository.login(
                _state.value.username,
                _state.value.password
            )
            if(success) navigator.push(AppScreen.Home)
            else _state.value = _state.value.copy(
                isLoading =  false,
                error = "Invalid credentials"
            )
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                isLoading = false,
                error = "Login Failed: ${e.message}"
            )
        }
    }
}