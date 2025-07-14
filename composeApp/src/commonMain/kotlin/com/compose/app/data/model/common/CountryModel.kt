package com.compose.app.data.model.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class CountryResponse(
    @SerialName("header")
    val header: List<String>? = null,

    @SerialName("data_list")
    val dataList: List<CountryModel>? = null
)

@Serializable
data class CountryModel(
    @SerialName("country_code")
    val countryCode: Int? = null,

    @SerialName("country_name")
    val countryName: String? = null,

    @SerialName("country_dialing_code")
    val countryDialingCode: String? = null,

    @SerialName("two_leter_country_code")
    val twoLeterCountryCode: String? = null,

    @SerialName("three_leter_country_code")
    val threeLeterCountryCode: String? = null,

    @SerialName("is_buyer")
    val isBuyer: Boolean? = null,

    @Transient
    val isSelected: Boolean = false
)