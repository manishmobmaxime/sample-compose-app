package com.compose.app.data.local.database

import androidx.room.RoomDatabase

expect class DatabaseDriverFactory {
    fun createDriver(): RoomDatabase.Builder<AppDatabase>
}