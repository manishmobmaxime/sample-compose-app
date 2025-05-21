package com.compose.app.presentation.ui.home

data class HomeState(
    val clickCount: Int = 0,          // Track button clicks
    val isLoading: Boolean = false,   // For loading indicators
    val error: String? = null        // To show errors
)