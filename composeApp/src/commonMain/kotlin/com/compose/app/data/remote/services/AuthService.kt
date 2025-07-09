package com.compose.app.data.remote.services

import com.compose.app.data.model.common.ApiResponse
import com.compose.app.data.model.common.DataWrapper
import com.compose.app.data.model.login.LoginRequest
import com.compose.app.data.model.login.LoginResponse
import com.compose.app.data.remote.ApiRoutes
import com.compose.app.data.remote.BaseService
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.encodedPath

interface AuthService {
    suspend fun login(request: LoginRequest): ApiResponse<LoginResponse>
}

class AuthServiceImpl(private val service: BaseService) : AuthService {

    override suspend fun login(request: LoginRequest): ApiResponse<LoginResponse> {
        return BaseService.client.post {
            url {
                encodedPath = ApiRoutes.LOGIN_URL
            }
            setBody(DataWrapper(request))
        }.body<ApiResponse<LoginResponse>>()
    }
}