package com.compose.app.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class ThemeMode {
    SYSTEM, LIGHT, DARK
}

interface SettingsRepository {
    // Authentication Token
    val authToken: Flow<String?>
    suspend fun setAuthToken(token: String?)

    // Dark Mode Setting
    // We'll change this to Flow<ThemeMode> to match our theme logic
    val themeMode: Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)
}

class SettingsRepositoryImpl(private val dataStore: DataStore<Preferences>) : SettingsRepository {

    // Define keys for your preferences (can remain internal to the implementation)
    private object PreferencesKeys {
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val THEME_MODE = stringPreferencesKey("theme_mode")
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

    override val themeMode: Flow<ThemeMode> = dataStore.data.map { preferences ->
        // Default to SYSTEM if not set or invalid
        when (preferences[PreferencesKeys.THEME_MODE]) {
            ThemeMode.LIGHT.name -> ThemeMode.LIGHT
            ThemeMode.DARK.name -> ThemeMode.DARK
            else -> ThemeMode.SYSTEM
        }
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME_MODE] = mode.name
        }
    }
}