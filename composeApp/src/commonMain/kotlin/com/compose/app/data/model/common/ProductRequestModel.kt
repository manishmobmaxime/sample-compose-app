package com.compose.app.data.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductRequestModel(
    @SerialName("param")
    val param: List<ParamModel>?,

    @SerialName("offset")
    val offset: Int? = null,

    @SerialName("limit")
    val limit: Int? = null,
)