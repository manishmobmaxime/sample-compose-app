package com.compose.app

import com.compose.app.data.local.database.DatabaseDriverFactory
import com.compose.app.di.appModule
import com.compose.app.di.databaseModule
import com.compose.app.di.storageModule
import networkModule
import org.koin.core.context.startKoin

//This is called from iOS doInitKoin
fun initKoin() {
    startKoin {
        modules(
            appModule,
            networkModule,
            storageModule,
            databaseModule(DatabaseDriverFactory()),
        )
    }
}