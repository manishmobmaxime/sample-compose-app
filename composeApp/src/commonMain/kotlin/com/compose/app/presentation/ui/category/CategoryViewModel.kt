package com.compose.app.presentation.ui.category

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.compose.app.data.model.category.ProductCategoryModel
import com.compose.app.data.model.common.ParamRequest
import com.compose.app.data.repository.CategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CategoryViewModel(private  val  repository: CategoryRepository) : ScreenModel {

    private val _categoryState = MutableStateFlow(CategoryState())
    val categoryState: StateFlow<CategoryState> = _categoryState.asStateFlow()

    init {
        initialize()
    }

    private fun initialize() {
        screenModelScope.launch {
            delay(1000)
            try {
                val request = ParamRequest(
                    param = emptyList(),
                );
                val result = repository.getCategories(request)
                _categoryState.value = _categoryState.value.copy(isLoading = false, success = true, categoryList = result?.data, selectedCategory = result?.data?.first())
            } catch (e: Exception) {
                // Log the error for debugging
                println("Error fetching products: ${e.message}")
                _categoryState.value = _categoryState.value.copy(error = "Failed to load products: ${e.message ?: "Unknown error"}") // Set state to Error
            }
        }
    }

    fun setCategory(category : ProductCategoryModel) {
        _categoryState.value = _categoryState.value.copy(selectedCategory = category);
    }
}