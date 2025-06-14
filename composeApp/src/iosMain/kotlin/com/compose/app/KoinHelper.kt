package com.compose.app

import com.compose.app.di.appModule
import networkModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            appModule,
            networkModule
        )
    }
}