package com.compose.app

import android.app.Application
import com.compose.app.data.applicationContextForDataStore
import com.compose.app.data.local.database.DatabaseDriverFactory
import com.compose.app.di.appModule
import com.compose.app.di.databaseModule
import com.compose.app.di.storageModule
import networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SampleApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        applicationContextForDataStore = this.applicationContext // Crucial for Android DataStore

        //Initialize Koin
        startKoin {
            androidContext(this@SampleApplication)
            modules(appModule, networkModule, storageModule, databaseModule(DatabaseDriverFactory(applicationContext)))
        }
    }
}