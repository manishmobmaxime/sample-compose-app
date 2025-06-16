package com.compose.app.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.compose.app.presentation.ui.account.AccountPage
import com.compose.app.presentation.ui.home.HomeScreen


class MainPage : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<MainViewModel>()
        val selectedTabIndex by viewModel.selectedTabIndex.collectAsState()
        val tabs = listOf(HomeScreen(), AccountPage())
        Scaffold(
            bottomBar = {
                BottomNavigation(modifier = Modifier.navigationBarsPadding()) {
                    tabs.forEachIndexed { index, label ->
                        BottomNavigationItem(
                            selected = selectedTabIndex == index,
                            onClick = { viewModel.onTabSelected(index) },
                            icon = {
                                val icon = if (index == 0) "🏠" else "👤"
                                Text(icon)
                            },
                            label = {
                                val tabLabel = if (index == 0) "Home" else "Account"
                                Text(tabLabel) }
                        )
                    }
                }
            },
            content = { padding ->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)) {
                    tabs[selectedTabIndex].Content()
                }
            }

        )
    }
}

