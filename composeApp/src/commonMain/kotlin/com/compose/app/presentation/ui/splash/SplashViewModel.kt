package com.compose.app.presentation.ui.splash

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.compose.kotlin.data.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val authRepository: AuthRepository) : ScreenModel {
    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn
    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() = screenModelScope.launch {
        delay(1000)
        val loggedIn = authRepository.isLoggedIn()
        _isLoggedIn.value = loggedIn
    }
}