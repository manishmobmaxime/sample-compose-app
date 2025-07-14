package com.compose.app.data.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = context.getDatabasePath(DATABASE_NAME)

        return Room.databaseBuilder<AppDatabase>(context, dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
    }
}