package com.compose.app.data.model.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ProductModel(
    @SerialName("product_code")
    val productCode: Int?,

    @SerialName("discount_percentage")
    val discountPercentage: Int?,

    @SerialName("wishlist_code")
    val wishlistCode: JsonElement?,

    @SerialName("country_code")
    val countryCode: Int?,

    @SerialName("product_group_code")
    val productGroupCode: Int?,

    @SerialName("product_name")
    val productName: String?,

    @SerialName("version")
    val version: JsonElement?,

    @SerialName("quick_update")
    val quickUpdate: JsonElement?,

    @SerialName("product_category_name")
    val productCategoryName: String?,

    @SerialName("most_sale")
    val mostSale: String?,

    @SerialName("mp_product_order")
    val mpProductOrder: JsonElement?,

    @SerialName("product_subcategory_name")
    val productSubcategoryName: String?,

    @SerialName("product_group_name")
    val productGroupName: String?,

    @SerialName("product_ex_code")
    val productExCode: String?,

    @SerialName("brand_code")
    val brandCode: Int?,

    @SerialName("min_cap")
    val minCap: Double?,

    @SerialName("is_active")
    val isActive: Boolean?,

    @SerialName("product_subcategory_code")
    val productSubcategoryCode: Int?,

    @SerialName("rn")
    val rn: Int?,

    @SerialName("timestamp")
    val timestamp: JsonElement?, // Maps Dart's DateTime to kotlinx.datetime.Instant

    @SerialName("packs_per_package")
    val packsPerPackage: String?,

    @SerialName("sales_price")
    val salesPrice: Double?,

    @SerialName("hsn_code")
    val hsnCode: String?,

    @SerialName("brand_name")
    val brandName: String?,

    @SerialName("image")
    val image: String?,

    @SerialName("created_at")
    val createdAt: JsonElement?, // Maps Dart's DateTime to kotlinx.datetime.Instant

    @SerialName("manufacturer_code")
    val manufacturerCode: Int?,

    @SerialName("product_packing_type")
    val productPackingType: String?,

    @SerialName("cal_sale_price")
    val calSalePrice: Double?,

    @SerialName("original_price")
    val originalPrice: Double?,

    @SerialName("product_category_code")
    val productCategoryCode: Int?,

    @SerialName("product_status")
    val productStatus: String?,

    @SerialName("product_pack_type")
    val productPackType: String?,
)