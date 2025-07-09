package com.compose.app.data.repository

import com.compose.app.data.model.category.ProductCategoryModel
import com.compose.app.data.model.common.ApiResponse
import com.compose.app.data.model.common.CountryResponse
import com.compose.app.data.model.common.DataListResponse
import com.compose.app.data.model.common.ParamRequest
import com.compose.app.data.model.common.ProductRequestModel
import com.compose.app.data.model.home.BannerModel
import com.compose.app.data.model.home.BrandModel
import com.compose.app.data.model.home.ProductModel
import com.compose.app.data.remote.services.HomeService

interface HomeRepository {
    suspend fun getCountries(request: ParamRequest): ApiResponse<CountryResponse>?
    suspend fun getCategories(request: ParamRequest): ApiResponse<List<ProductCategoryModel>>?
    suspend fun getBanners(request: ParamRequest): ApiResponse<DataListResponse<BannerModel>>?
    suspend fun getBrands(request: ParamRequest): ApiResponse<DataListResponse<BrandModel>>?
    suspend fun getProducts(request: ProductRequestModel): ApiResponse<DataListResponse<ProductModel>>?
}

class HomeRepositoryImpl(private val homeService: HomeService) : HomeRepository {
    private var countryResponse: ApiResponse<CountryResponse>? = null
    private var categoryResponse: ApiResponse<List<ProductCategoryModel>>? = null
    private var bannerResponse: ApiResponse<DataListResponse<BannerModel>>? = null
    private var brandResponse: ApiResponse<DataListResponse<BrandModel>>? = null
    private var productResponse: ApiResponse<DataListResponse<ProductModel>>? = null
    override suspend fun getCountries(request: ParamRequest): ApiResponse<CountryResponse>? {
        return try {
            val response = homeService.getCountries(request)
            countryResponse = response
            countryResponse
        } catch (e: Exception) {
            // Log or handle error
            countryResponse
        }
    }

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

    override suspend fun getBanners(request: ParamRequest): ApiResponse<DataListResponse<BannerModel>>? {
        return try {
            val response = homeService.getBanners(request)
            bannerResponse = response
            bannerResponse
        } catch (e: Exception) {
            // Log or handle error
            bannerResponse
        }
    }

    override suspend fun getBrands(request: ParamRequest): ApiResponse<DataListResponse<BrandModel>>? {
        return try {
            val response = homeService.getBrands(request)
            brandResponse = response
            brandResponse
        } catch (e: Exception) {
            // Log or handle error
            brandResponse
        }
    }

    override suspend fun getProducts(request: ProductRequestModel): ApiResponse<DataListResponse<ProductModel>>? {
        return try {
            val response = homeService.getProducts(request)
            productResponse = response
            productResponse
        } catch (e: Exception) {
            // Log or handle error
            productResponse
        }
    }
}