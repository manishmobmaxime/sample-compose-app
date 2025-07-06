package com.compose.app.presentation.ui.category

import com.compose.app.data.model.category.ProductCategoryModel

data class CategoryState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val success: Boolean = false,
    val categoryList: List<ProductCategoryModel>? = null,
    val selectedCategory: ProductCategoryModel? = null,
)