package com.compose.app.data

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

// This is a helper function to create a DataStore for Android
fun createAndroidDataStore(
    context: Context,
    fileName: String = SETTINGS_PREFERENCES_NAME,
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = ReplaceFileCorruptionHandler { emptyPreferences() },
    migrations: List<DataMigration<Preferences>> = emptyList(),
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        corruptionHandler = corruptionHandler,
        migrations = migrations,
        scope = scope,
        produceFile = { context.preferencesDataStoreFile(fileName) }
    )
}

// Global context provider for Android
lateinit var applicationContextForDataStore: Context

actual fun createDataStore(): DataStore<Preferences> {
    if (!::applicationContextForDataStore.isInitialized) {
        throw IllegalStateException("Android Application Context for DataStore is not initialized. Call setApplicationContextForDataStore() in your Application class.")
    }
    return createAndroidDataStore(
        context = applicationContextForDataStore,
        fileName = SETTINGS_PREFERENCES_NAME
    )
}