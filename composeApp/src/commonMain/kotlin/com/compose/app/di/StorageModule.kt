package com.compose.app.di

import com.compose.app.data.SettingsRepository
import com.compose.app.data.SettingsRepositoryImpl
import com.compose.app.data.createDataStore
import com.compose.app.data.local.database.AppDatabase
import com.compose.app.data.local.database.DatabaseDriverFactory
import org.koin.dsl.module


val storageModule = module {
    // DataStore
    single { createDataStore() }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}

fun databaseModule(databaseDriverFactory: DatabaseDriverFactory) = module {
    single { databaseDriverFactory.createDriver().build() }

    // Defining database access objects (DAO)
    single { get<AppDatabase>().countryDao() }
}