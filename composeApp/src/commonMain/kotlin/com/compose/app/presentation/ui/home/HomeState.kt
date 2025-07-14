package com.compose.app.presentation.ui.home

import com.compose.app.data.model.category.ProductCategoryModel
import com.compose.app.data.model.common.CountryModel
import com.compose.app.data.model.home.BannerModel
import com.compose.app.data.model.home.BrandModel
import com.compose.app.data.model.home.ProductModel

data class HomeState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val success: Boolean = false,
    val showCountrySheet: Boolean = false,
    val categoryList: List<ProductCategoryModel>? = null,
    val selectedCategory: ProductCategoryModel? = null,
    val countryList: List<CountryModel>? = null,
    var selectedCountry: CountryModel? = null,
    val bannerList: List<BannerModel>? = null,
    val brandList: List<BrandModel>? = null,
    val newArrivalProductsList: List<ProductModel>? = null,
)