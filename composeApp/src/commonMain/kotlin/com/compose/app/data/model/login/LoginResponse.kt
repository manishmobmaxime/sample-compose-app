package com.compose.app.data.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("is_approved")
    val isApproved: Boolean,

    @SerialName("is_detail_available")
    val isDetailAvailable: Boolean,

    @SerialName("is_admin")
    val isAdmin: Boolean,

    @SerialName("is_manufacturer")
    val isManufacturer: Boolean,

    @SerialName("is_google_user")
    val isGoogleUser: Boolean,

    val token: String,

    @SerialName("user_code")
    val userCode: String,

    val email: String,

    @SerialName("customer_name")
    val customerName: String
)