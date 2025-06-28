package com.compose.app.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light Theme Colors
val LightPrimary = Color(0xFF005693) // A nice purple
val LightOnPrimary = Color(0xFFFFFFFF)
val LightSecondary = Color(0xFF03DAC5) // A teal accent
val LightOnSecondary = Color(0xFF000000)
val LightBackground = Color(0xFFFFFFFF)
val LightOnBackground = Color(0xFF000000)
val LightSurface = Color(0xFFFFFFFF)
val LightOnSurface = Color(0xFF000000)
val LightError = Color(0xFFB00020) // A standard red for errors
val LightOnError = Color(0xFFFFFFFF)

// Dark Theme Colors
val DarkPrimary = Color(0xFFBB86FC) // A lighter purple for dark mode
val DarkOnPrimary = Color(0xFF000000)
val DarkSecondary = Color(0xFF03DAC5) // Same teal, but might adjust in real apps
val DarkOnSecondary = Color(0xFF000000)
val DarkBackground = Color(0xFF121212) // Dark grey
val DarkOnBackground = Color(0xFFFFFFFF)
val DarkSurface = Color(0xFF121212) // Same as background for simplicity
val DarkOnSurface = Color(0xFFFFFFFF)
val DarkError = Color(0xFFCF6679) // A muted red for errors
val DarkOnError = Color(0xFF000000)

val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    error = LightError,
    onError = LightOnError
)

val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    error = DarkError,
    onError = DarkOnError
)