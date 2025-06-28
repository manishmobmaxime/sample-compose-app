package com.compose.app.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.compose.app.data.model.common.ParamRequest
import com.compose.app.data.model.home.ProductModel
import com.compose.app.data.repository.AuthRepository
import com.compose.app.data.repository.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

sealed class ProductListState {
    data object Loading : ProductListState() // Data is being fetched
    data class Success(val products: List<ProductModel>?) : ProductListState() // Data fetched successfully
    data class Error(val message: String) : ProductListState() // An error occurred
    data object Idle : ProductListState() // Initial state, or after an action
}

class HomeViewModel(private  val  homeRepository: HomeRepository) :  ScreenModel {
    private val _isLogout = MutableStateFlow(false)
    val isLogout: StateFlow<Boolean> = _isLogout

    private val _productsState = MutableStateFlow<ProductListState>(ProductListState.Idle)
    val productsState: StateFlow<ProductListState> = _productsState.asStateFlow()

    fun onLogoutSuccess() {
        _isLogout.value = false
    }

    init {
        initialize()
    }

    private fun initialize() {
        screenModelScope.launch {
            _productsState.value = ProductListState.Loading // Set state to Loading
            delay(1000)
//        val request = ParamRequest(
//            param = emptyList(),
//            isCallingFromHome = false,
//        );
//        homeRepository.getCategories(request);
            try {
                val products = homeRepository.getProducts()
                _productsState.value = ProductListState.Success(products) // Set state to Success with data
            } catch (e: Exception) {
                // Log the error for debugging
                println("Error fetching products: ${e.message}")
                _productsState.value = ProductListState.Error("Failed to load products: ${e.message ?: "Unknown error"}") // Set state to Error
            }
        }
    }
}