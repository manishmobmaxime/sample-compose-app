package com.compose.kotlin.presentation.ui.home

sealed interface HomeEvent {
    data object ButtonClicked : HomeEvent  // User clicked button
    data object RefreshData : HomeEvent   // Example: could add later
    data class SearchQueryChanged(val query: String) : HomeEvent // Example
}