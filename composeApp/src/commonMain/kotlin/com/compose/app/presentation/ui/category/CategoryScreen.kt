package com.compose.app.presentation.ui.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.compose.app.presentation.components.CustomAppBar

class CategoryScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                CustomAppBar(
                    title = "Category",
                )
            },
            content = { paddingValues ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Category")
                }
            }
        )
    }
}