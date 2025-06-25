package com.compose.app.data.repository

import com.compose.app.data.model.common.ApiResponse
import com.compose.app.data.model.login.LoginRequest
import com.compose.app.data.model.login.LoginResponse
import com.compose.app.data.remote.AuthService

interface AuthRepository {
    suspend fun login(username: String, password: String): ApiResponse<LoginResponse>?
    fun isLoggedIn(): Boolean
    fun logout()
}

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {

    private var token: String? = null
    private var loginResponse: ApiResponse<LoginResponse>? = null
    private var loggedIn = false

    override suspend fun login(username: String, password: String): ApiResponse<LoginResponse>? {
        if(username.isNotBlank() && password.isNotBlank()) {
            return try {
                val request = LoginRequest(
                    email = username,
                    password = password
                )
                val response = authService.login(request)
                loginResponse = response
                loginResponse
            } catch (e: Exception) {
                // Log or handle error
                loginResponse
            }
        } else {
            return loginResponse
        }
    }

    override fun isLoggedIn(): Boolean = loggedIn

    override fun logout() {
        loggedIn = false
        token = null
    }
}