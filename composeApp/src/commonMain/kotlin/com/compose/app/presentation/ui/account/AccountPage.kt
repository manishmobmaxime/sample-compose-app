package com.compose.app.presentation.ui.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
import com.compose.app.presentation.navigation.AppScreen
import com.compose.app.presentation.ui.login.LoginScreen

class AccountPage : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.koinNavigatorScreenModel<AccountViewModel>()
        val isLogout by viewModel.isLogout.collectAsState()

        LaunchedEffect(isLogout) {
            isLogout.let {
                if (it) {
                    navigator.replaceAll(LoginScreen())
                    viewModel.onLogoutSuccess()
                }
            }
        }
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text("Account Detail")
            Button(onClick = {viewModel.logout()}) {
                Text("Logout")
            }
        }
    }
}