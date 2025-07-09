package com.compose.app.data.model.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrandModel(
    @SerialName("brand_code")
    val brandCode: Int? = null,

    @SerialName("brand_name")
    val brandName: String? = null,

    @SerialName("image")
    val image: String? = null
)