package com.compose.kotlin.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.compose.kotlin.data.repository.AuthRepository
import com.compose.kotlin.presentation.navigation.AppScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(private val authRepository: AuthRepository, private  val navigator: Navigator) : ViewModel() {

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() = viewModelScope.launch {
        delay(2000)
        if(authRepository.isLoggedIn()) {
            navigator.replaceAll(AppScreen.Home)
        } else {
            navigator.replaceAll(AppScreen.Login)
        }
    }
}