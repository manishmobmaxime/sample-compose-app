package com.compose.app.data.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ParamModel(
    @SerialName("propertyname")
    val propertyName: String? = null,

    @SerialName("operator")
    val operator: JsonElement? = null,

    @SerialName("propertyvalue")
    val propertyValue: JsonElement? = null,

    @SerialName("PropertyName")
    val propertyNameCap: String? = null,

    @SerialName("Operator")
    val operatorCap: JsonElement? = null,

    @SerialName("PropertyValue")
    val propertyValueCap: JsonElement? = null
)