package com.compose.app.data.model.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)