package com.compose.app

import com.compose.app.di.appModule
import networkModule
import org.koin.core.context.startKoin

//This is called from iOS doInitKoin
fun initKoin() {
    startKoin {
        modules(
            appModule,
            networkModule
        )
    }
}