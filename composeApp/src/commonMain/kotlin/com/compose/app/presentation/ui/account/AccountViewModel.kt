package com.compose.app.presentation.ui.account

import cafe.adriel.voyager.core.model.ScreenModel
import com.compose.app.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AccountViewModel(private  val  authRepository: AuthRepository) : ScreenModel {
    private val _isLogout = MutableStateFlow(false)
    val isLogout: StateFlow<Boolean> = _isLogout
    fun logout() {
        authRepository.logout()
        _isLogout.value = true
    }

    fun onLogoutSuccess() {
        _isLogout.value = false
    }
}