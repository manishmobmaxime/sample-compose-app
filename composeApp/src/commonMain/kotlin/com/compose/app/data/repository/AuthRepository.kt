package com.compose.kotlin.data.repository

interface AuthRepository {
    suspend fun login(username: String, password: String): Boolean
    fun isLoggedIn(): Boolean
    fun logout()
}