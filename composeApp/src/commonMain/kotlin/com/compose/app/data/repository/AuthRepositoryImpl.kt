package com.compose.kotlin.data.repository

class AuthRepositoryImpl : AuthRepository {
    private var loggedIn = false

    override suspend fun login(username: String, password: String): Boolean {
        loggedIn = username.isNotBlank() && password.isNotBlank()
        return loggedIn
    }

    override fun isLoggedIn(): Boolean = loggedIn

    override fun logout() {
        loggedIn = false
    }
}