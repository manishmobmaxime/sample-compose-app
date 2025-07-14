package com.compose.app.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun CountryFlag(countryCode: String) {
    val url = "https://flagcdn.com/w80/${countryCode.lowercase()}.png" // 2-letter ISO country code

    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = Modifier.size(30.dp).clip(RoundedCornerShape(4.dp))
    )
}