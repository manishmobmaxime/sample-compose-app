package com.compose.app.data.model.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ProductCategoryResponse(
    @SerialName("Classes")
    val classes: List<ProductCategoryModel>? = null
)

@Serializable
data class ProductCategoryModel(
    @SerialName("product_category_code")
    val productCategoryCode: Int? = null,

    @SerialName("product_category_name")
    val productCategoryName: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("image")
    val image: String? = null,

    @SerialName("is_direct_buy")
    val isDirectBuy: Boolean? = null,

    @SerialName("commission_percent")
    val commissionPercent: Double? = null,

    @SerialName("product_subcategory")
    val productSubCategory: List<ProductSubCategoryModel>? = null,

    @SerialName("category_banner")
    val categoryBanner: List<String>? = null, // or List<JsonElement> if unknown structure

    @SerialName("mobile_image")
    val mobileImage: String? = null,

    @SerialName("tablet_image")
    val tableImage: String? = null
)

@Serializable
data class ProductSubCategoryModel(
    @SerialName("product_subcategory_code")
    val productSubCategoryCode: Int? = null,

    @SerialName("product_subcategory_name")
    val productSubCategoryName: String? = null,

    @SerialName("product_category_code")
    val productCategoryCode: Int? = null,

    @SerialName("product_category_name")
    val productCategoryName: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("image")
    val image: String? = null,

    @SerialName("view_image")
    val viewImage: String? = null,

    @SerialName("home_image")
    val homeImage: String? = null,

    @SerialName("product_count")
    val productCount: Int? = null,

    @SerialName("product_group")
    val productGroup: List<ProductGroupModel>? = null
)

@Serializable
data class ProductGroupModel(
    @SerialName("product_category_code")
    val productCategoryCode: Int? = null,

    @SerialName("product_category_name")
    val productCategoryName: String? = null,

    @SerialName("product_subcategory_code")
    val productSubcategoryCode: Int? = null,

    @SerialName("product_subcategory_name")
    val productSubcategoryName: String? = null,

    @SerialName("product_group_code")
    val productGroupCode: Int? = null,

    @SerialName("product_group_name")
    val productGroupName: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("image")
    val image: String? = null,

    @SerialName("product_subgroup")
    val productSubgroup: List<String>? = null // or JsonElement if it's complex
)