package com.compose.app.data.remote

import com.compose.app.data.model.common.ApiResponse
import com.compose.app.data.model.login.LoginRequest
import com.compose.app.data.model.login.LoginResponse
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.encodedPath
import kotlinx.serialization.Serializable

interface AuthService {
    suspend fun login(request: LoginRequest): ApiResponse<LoginResponse>
}

@Serializable
data class DataWrapper<T>(val data: T)

class AuthServiceImpl(private val service: BaseService) : AuthService {

    override suspend fun login(request: LoginRequest): ApiResponse<LoginResponse> {
        return service.client.post {
            url {
                encodedPath = ApiRoutes.LOGIN_URL
            }
            setBody(DataWrapper(request))
        }.body<ApiResponse<LoginResponse>>()
    }
}