package com.compose.app.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object BaseService {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json { 
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            url(urlString = ApiRoutes.BASE_URL)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 10000
            socketTimeoutMillis = 10000
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("KtorClient: $message")
                }

            }
        }

        expectSuccess = true
    }

    suspend inline fun <reified T> handleResponse(response: HttpResponse): Result<T> {
        return when (response.status) {
            HttpStatusCode.OK -> {
                val body = response.body<T>()
                Result.success(body)
            }
            HttpStatusCode.Unauthorized -> {
                Result.failure(Exception("Invalid credentials"))
            }
            HttpStatusCode.BadRequest -> {
                Result.failure(Exception("Invalid request format"))
            }
            else -> {
                Result.failure(Exception("Request failed: ${response.status.description}"))
            }
        }
    }
}