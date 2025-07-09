package com.compose.app.data.repository
import com.compose.app.data.model.category.ProductCategoryModel
import com.compose.app.data.model.common.ApiResponse
import com.compose.app.data.model.common.ParamRequest
import com.compose.app.data.remote.services.HomeService

interface CategoryRepository {
    suspend fun getCategories(request: ParamRequest): ApiResponse<List<ProductCategoryModel>>?
}

class CategoryRepositoryImpl(private val homeService: HomeService) : CategoryRepository {

    private var categoryResponse: ApiResponse<List<ProductCategoryModel>>? = null

    override suspend fun getCategories(request: ParamRequest): ApiResponse<List<ProductCategoryModel>>? {
        return try {
            val response = homeService.getCategories(request)
            categoryResponse = response
            categoryResponse
        } catch (e: Exception) {
            // Log or handle error
            categoryResponse
        }
    }
}