package com.compose.app.presentation.ui.home


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compose.app.presentation.components.CustomAppBar
import com.compose.app.presentation.ui.login.LoginScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.koinNavigatorScreenModel<HomeViewModel>()
        val isLogout by viewModel.isLogout.collectAsState()

        LaunchedEffect(isLogout) {
            isLogout.let {
                if (it) {
                    navigator.replaceAll(LoginScreen())
                    viewModel.onLogoutSuccess()
                }
            }
        }
        Scaffold(
            topBar = {
                CustomAppBar(
                    title = "Home",
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Text(
                        text = "Welcome to my app!",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        )
    }
}