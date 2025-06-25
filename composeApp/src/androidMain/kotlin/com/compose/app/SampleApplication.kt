package com.compose.app

import android.app.Application
import com.compose.app.data.applicationContextForDataStore
import com.compose.app.di.appModule
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
            modules(appModule, networkModule)
        }
    }
}