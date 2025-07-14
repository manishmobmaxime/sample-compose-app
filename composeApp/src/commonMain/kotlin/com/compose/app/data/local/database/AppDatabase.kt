package com.compose.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.app.data.local.database.daos.CountryDao
import com.compose.app.data.local.database.entities.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}

internal const val DATABASE_NAME = "app_database.db"