package com.compose.app.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingsRepository {
    // Authentication Token
    val authToken: Flow<String?>
    suspend fun setAuthToken(token: String?)

    // Dark Mode Setting
    val isDarkMode: Flow<Boolean>
    suspend fun setIsDarkMode(isDark: Boolean)
}

class SettingsRepositoryImpl(private val dataStore: DataStore<Preferences>) : SettingsRepository {

    // Define keys for your preferences (can remain internal to the implementation)
    private object PreferencesKeys {
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        // Add other keys here as you need them
    }

    override val authToken: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.AUTH_TOKEN]
    }

    override suspend fun setAuthToken(token: String?) {
        dataStore.edit { preferences ->
            if (token != null) {
                preferences[PreferencesKeys.AUTH_TOKEN] = token
            } else {
                preferences.remove(PreferencesKeys.AUTH_TOKEN)
            }
        }
    }

    override val isDarkMode: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.IS_DARK_MODE] ?: false // Default to false if not set
    }

    override suspend fun setIsDarkMode(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] = isDark
        }
    }
}