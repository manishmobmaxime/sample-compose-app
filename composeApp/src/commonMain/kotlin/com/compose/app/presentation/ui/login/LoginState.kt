package com.compose.app.presentation.ui.login

data class LoginState(
    val username : String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)