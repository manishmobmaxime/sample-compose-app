package com.compose.app.presentation.ui.splash

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.compose.app.data.SettingsRepository
import com.compose.app.data.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SplashViewModel(private val settings: SettingsRepository) : ScreenModel {
    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn
    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() = screenModelScope.launch {
        delay(1000)
//        val loggedIn = authRepository.isLoggedIn()
        val loggedIn: kotlinx.coroutines.flow.Flow<Boolean> = (settings.authToken).map { token -> !token.isNullOrBlank() }
        _isLoggedIn.value = loggedIn.first()
    }
}