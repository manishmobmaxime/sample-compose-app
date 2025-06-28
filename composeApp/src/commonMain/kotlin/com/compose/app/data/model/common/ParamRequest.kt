package com.compose.app.data.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ParamRequest(
    @SerialName("param")
    val param: List<ParamModel>? = null,

    @SerialName("isCallingFromHome")
    val isCallingFromHome: Boolean? = null
)