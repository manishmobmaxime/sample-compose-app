package com.compose.app.data.remote

import com.compose.app.data.model.category.ProductCategoryResponse
import com.compose.app.data.model.common.ApiResponse
import com.compose.app.data.model.common.DataWrapper
import com.compose.app.data.model.common.ParamRequest
import com.compose.app.data.model.home.ProductModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.http.encodedPath

interface HomeService {
    suspend fun getCategories(request: ParamRequest): ApiResponse<ProductCategoryResponse>?
    suspend fun getProducts(): List<ProductModel>
}

class HomeServiceImpl(private val service: BaseService) : HomeService {

//     [GET] Categories
    override suspend fun getCategories(request: ParamRequest): ApiResponse<ProductCategoryResponse> {
        return service.client.get {
            url {
                encodedPath = ApiRoutes.CATEGORY_LIST_URL
            }
            setBody(DataWrapper(request))
        }.body<ApiResponse<ProductCategoryResponse>>()
    }

    override suspend fun getProducts(): List<ProductModel> {
        return service.client.get {
            url {
                encodedPath = ApiRoutes.PRODUCT_LIST_URL
            }
        }.body<List<ProductModel>>()
    }
}