package com.compose.kotlin.presentation.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.compose.app.presentation.ui.home.HomeScreen
import com.compose.app.presentation.ui.login.LoginScreen
import com.compose.app.presentation.ui.splash.SplashScreen

sealed interface AppScreen : Screen {
    data object Splash : AppScreen {
        @Composable
        override fun Content() {
            // Your splash screen content here
            SplashScreen().Content()
        }
    }

    data object Login : AppScreen {
        @Composable
        override fun Content() {
            // Your login screen content here
            LoginScreen().Content()
        }
    }

    data object Home : AppScreen {
        @Composable
        override fun Content() {
            // Your home screen content here
            HomeScreen().Content()
        }
    }
}