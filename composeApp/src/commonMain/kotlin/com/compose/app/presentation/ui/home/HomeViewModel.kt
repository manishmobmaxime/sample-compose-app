package com.compose.kotlin.presentation.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.compose.kotlin.data.repository.AuthRepository
import com.compose.kotlin.presentation.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(private  val  authRepository: AuthRepository, private  val navigator: Navigator) : ViewModel() {
    fun logout() {
        authRepository.logout()
        navigator.replaceAll(AppScreen.Login)
    }
}