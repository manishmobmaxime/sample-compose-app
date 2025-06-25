package com.compose.app.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

internal const val SETTINGS_PREFERENCES_NAME = "app_settings.preferences_pb"

expect fun createDataStore(): DataStore<Preferences>