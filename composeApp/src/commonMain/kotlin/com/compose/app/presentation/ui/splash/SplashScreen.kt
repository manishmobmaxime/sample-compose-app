package com.compose.app.presentation.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compose.app.presentation.ui.MainPage
import com.compose.app.presentation.ui.home.HomeScreen
import com.compose.app.presentation.ui.login.LoginScreen

class SplashScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.koinNavigatorScreenModel<SplashViewModel>()
        val isLoggedIn by viewModel.isLoggedIn.collectAsState()

        LaunchedEffect(isLoggedIn) {
            isLoggedIn?.let {
                if (it) {
                    navigator.replace(MainPage())
                } else {
                    navigator.replace(LoginScreen())
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("My App Splash Screen")
        }
    }

}