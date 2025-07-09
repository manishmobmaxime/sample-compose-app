package com.compose.app.data.remote.services

import com.compose.app.data.model.category.ProductCategoryModel
import com.compose.app.data.model.common.ApiResponse
import com.compose.app.data.model.common.CountryResponse
import com.compose.app.data.model.common.DataListResponse
import com.compose.app.data.model.common.DataWrapper
import com.compose.app.data.model.common.ParamRequest
import com.compose.app.data.model.common.ProductRequestModel
import com.compose.app.data.model.home.BannerModel
import com.compose.app.data.model.home.BrandModel
import com.compose.app.data.model.home.ProductModel
import com.compose.app.data.remote.ApiRoutes
import com.compose.app.data.remote.BaseService
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.encodedPath

interface HomeService {
    suspend fun getCountries(request: ParamRequest): ApiResponse<CountryResponse>?
    suspend fun getCategories(request: ParamRequest): ApiResponse<List<ProductCategoryModel>>?
    suspend fun getBanners(request: ParamRequest): ApiResponse<DataListResponse<BannerModel>>?
    suspend fun getBrands(request: ParamRequest): ApiResponse<DataListResponse<BrandModel>>?
    suspend fun getProducts(request: ProductRequestModel): ApiResponse<DataListResponse<ProductModel>>?
}

class HomeServiceImpl(private val service: BaseService) : HomeService {
    // [GET] Countries
    override suspend fun getCountries(request: ParamRequest): ApiResponse<CountryResponse> {
        return BaseService.client.post {
            url {
                encodedPath = ApiRoutes.COUNTRY_LIST_URL
            }
            setBody(DataWrapper(request))
        }.body<ApiResponse<CountryResponse>>()
    }

    // [GET] Categories
    override suspend fun getCategories(request: ParamRequest): ApiResponse<List<ProductCategoryModel>> {
        return BaseService.client.post {
            url {
                encodedPath = ApiRoutes.CATEGORY_LIST_URL
            }
            setBody(DataWrapper(request))
        }.body<ApiResponse<List<ProductCategoryModel>>>()
    }

    // [GET] Banners
    override suspend fun getBanners(request: ParamRequest): ApiResponse<DataListResponse<BannerModel>> {
        return BaseService.client.post {
            url {
                encodedPath = ApiRoutes.BANNER_LIST_URL
            }
            setBody(DataWrapper(request))
        }.body<ApiResponse<DataListResponse<BannerModel>>>()
    }

    // [GET] Brands
    override suspend fun getBrands(request: ParamRequest): ApiResponse<DataListResponse<BrandModel>> {
        return BaseService.client.post {
            url {
            encodedPath = ApiRoutes.BRAND_LIST_URL
            }
            setBody(DataWrapper(request))
        }.body<ApiResponse<DataListResponse<BrandModel>>>()
    }

    // [GET] Products
    override suspend fun getProducts(request: ProductRequestModel): ApiResponse<DataListResponse<ProductModel>> {
        return BaseService.client.post {
            url {
                encodedPath = ApiRoutes.PRODUCT_LIST_URL
            }
            setBody(DataWrapper(request))
        }.body<ApiResponse<DataListResponse<ProductModel>>>()
    }
}