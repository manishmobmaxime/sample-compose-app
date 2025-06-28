package com.compose.app.data.repository

import com.compose.app.data.model.category.ProductCategoryResponse
import com.compose.app.data.model.common.ApiResponse
import com.compose.app.data.model.common.ParamRequest
import com.compose.app.data.model.home.ProductModel
import com.compose.app.data.remote.HomeService

interface HomeRepository {
    suspend fun getCategories(request: ParamRequest): ApiResponse<ProductCategoryResponse>?
    suspend fun getProducts(): List<ProductModel>?
}

class HomeRepositoryImpl(private val homeService: HomeService) : HomeRepository {

    private var categoryResponse: ApiResponse<ProductCategoryResponse>? = null
    private var productResponse: List<ProductModel>? = null

    override suspend fun getCategories(request: ParamRequest): ApiResponse<ProductCategoryResponse>? {
        return try {
            val response = homeService.getCategories(request)
            categoryResponse = response
            categoryResponse
        } catch (e: Exception) {
            // Log or handle error
            categoryResponse
        }
    }

    override suspend fun getProducts(): List<ProductModel>? {
        return try {
            val response = homeService.getProducts()
            productResponse = response
            productResponse
        } catch (e: Exception) {
            // Log or handle error
            productResponse
        }
    }
}