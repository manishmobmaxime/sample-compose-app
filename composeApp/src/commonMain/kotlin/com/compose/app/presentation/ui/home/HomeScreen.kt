package com.compose.app.presentation.ui.home


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compose.kotlin.presentation.navigation.AppScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.koinNavigatorScreenModel<HomeViewModel>()
        val isLogout by viewModel.isLogout.collectAsState()

        LaunchedEffect(isLogout) {
            isLogout.let {
                if (it) {
                    navigator.replaceAll(AppScreen.Login)
                    viewModel.onLogoutSuccess()
                }
            }
        }
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text("Welcome!")
            Button(onClick = {viewModel.logout()}) {
                Text("Logout")
            }
        }
    }
}