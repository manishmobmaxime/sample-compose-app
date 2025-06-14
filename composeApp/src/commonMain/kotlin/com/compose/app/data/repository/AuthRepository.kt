package com.compose.app.data.repository

import com.compose.app.data.model.login.LoginRequest
import com.compose.app.data.remote.AuthService

interface AuthRepository {
    suspend fun login(username: String, password: String): Boolean
    fun isLoggedIn(): Boolean
    fun logout()
}

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {

    private var token: String? = null
    private var loggedIn = false

    override suspend fun login(username: String, password: String): Boolean {
        if(username.isNotBlank() && password.isNotBlank()) {
            return try {
                val request = LoginRequest(
                    email = username,
                    password = password
                )
                val response = authService.login(request)
                loggedIn = response.isSuccess
                loggedIn
            } catch (e: Exception) {
                // Log or handle error
                loggedIn = false
                false
            }
        } else {
            return  false
        }
    }

    override fun isLoggedIn(): Boolean = loggedIn

    override fun logout() {
        loggedIn = false
        token = null
    }
}