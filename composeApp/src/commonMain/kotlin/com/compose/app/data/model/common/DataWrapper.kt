package com.compose.app.data.model.common

import kotlinx.serialization.Serializable

@Serializable
data class DataWrapper<T>(val data: T)