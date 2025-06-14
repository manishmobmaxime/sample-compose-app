package com.compose.app.data.model.common

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val code: Int,
    val message: String? = null,
    val data: T? = null
) {
    val isSuccess: Boolean
        get() = code == 200
}

