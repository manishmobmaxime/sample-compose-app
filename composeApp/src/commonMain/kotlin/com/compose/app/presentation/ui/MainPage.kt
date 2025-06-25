package com.compose.app.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.compose.app.presentation.ui.account.AccountPage
import com.compose.app.presentation.ui.home.HomeScreen
import org.jetbrains.compose.resources.painterResource
import samplecomposeapp.composeapp.generated.resources.Res
import samplecomposeapp.composeapp.generated.resources.ic_cart
import samplecomposeapp.composeapp.generated.resources.ic_category
import samplecomposeapp.composeapp.generated.resources.ic_home
import samplecomposeapp.composeapp.generated.resources.ic_user

class MainPage : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<MainViewModel>()
        val selectedTabIndex by viewModel.selectedTabIndex.collectAsState()
        val tabs = listOf(HomeScreen(), AccountPage(), HomeScreen(), AccountPage())
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background, // Scaffold background
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                // Material 3 NavigationBar
                NavigationBar(
                    modifier = Modifier.navigationBarsPadding(), // Apply padding directly to the bar
                    containerColor = MaterialTheme.colorScheme.surface, // Use surface for bar background
                    contentColor = MaterialTheme.colorScheme.onSurface, // Use onSurface for default item content
                    tonalElevation = 1.dp, // <--- Set tonal elevation to 0.dp to remove color tint
                ) {
                    tabs.forEachIndexed { index, label ->
                        val isSelected = selectedTabIndex == index
                        val iconRes = when (index) {
                            0 -> Res.drawable.ic_home
                            1 -> Res.drawable.ic_category
                            2 -> Res.drawable.ic_cart
                            3 -> Res.drawable.ic_user
                            else -> Res.drawable.ic_user
                        }

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { viewModel.onTabSelected(index) },
                            icon = {
                                Image(
                                    painter = painterResource(iconRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = ColorFilter.tint(
                                        // Use MaterialTheme colors for selected/unselected states
                                        if (isSelected) MaterialTheme.colorScheme.primary // Or primaryContainer, secondary
                                        else MaterialTheme.colorScheme.onSurfaceVariant // Or onSurface.mediumEmphasis
                                    )
                                )
                            },
                            label = {
                                val tabLabel = if (index == 0) "Home" else if(index == 0) "Category" else if(index == 0) "Cart" else "Account"
                                Text(
                                    tabLabel,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            // Optional: Customize colors for the NavigationBarItem
//                            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
//                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
//                                selectedTextColor = MaterialTheme.colorScheme.onSurface,
//                                indicatorColor = MaterialTheme.colorScheme.primaryContainer, // Background color when selected
//                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
//                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
//                            )
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

