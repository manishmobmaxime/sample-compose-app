package com.compose.app.data.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataListResponse<T>(
    @SerialName("header")
    val header: List<String>? = null,

    @SerialName("data_list")
    val dataList: List<T>? = null
)