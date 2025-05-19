package com.compose.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform