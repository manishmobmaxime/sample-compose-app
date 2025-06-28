package com.compose.app.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomAppBar(
    title: String,
    canNavigateBack: Boolean = false,
    onNavigateBack: () -> Unit = {},
    modifier: Modifier = Modifier, // Provide a default Modifier for previews
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color  = MaterialTheme.colorScheme.onSurface,
) { // Changed return type from ": @Composable () -> Unit" to just "Unit" (implied)
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge ,// Use appropriate typography from your theme
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back", // Good for accessibility
                        tint = contentColor // Ensure icon color matches content
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = contentColor,
            navigationIconContentColor = contentColor
        ),
        modifier = modifier.shadow(1.dp),
    )
}