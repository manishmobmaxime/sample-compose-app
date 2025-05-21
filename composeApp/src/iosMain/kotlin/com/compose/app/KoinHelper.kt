package com.compose.app

import com.compose.kotlin.di.appModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            appModule
        )
    }
}