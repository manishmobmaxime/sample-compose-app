package com.compose.app.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun CountryFlag(countryCode: String) {
    val url = "https://flagcdn.com/w80/${countryCode.lowercase()}.png" // 2-letter ISO country code

    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.size(24.dp)
    )
}