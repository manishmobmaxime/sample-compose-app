package com.compose.app.data.model.home

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class ProductModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating // Nested data class for the rating object
)

@Serializable
data class Rating(
    val rate: Double,
    val count: Int
)