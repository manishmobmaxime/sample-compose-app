package com.compose.app.presentation.ui.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.compose.app.data.local.database.daos.CountryDao
import com.compose.app.data.local.database.entities.CountryEntity
import com.compose.app.data.mappers.toEntity
import com.compose.app.data.mappers.toModel
import com.compose.app.data.model.common.CountryModel
import com.compose.app.data.model.common.ParamModel
import com.compose.app.data.model.common.ParamRequest
import com.compose.app.data.model.common.ProductRequestModel
import com.compose.app.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository, private val countryDao: CountryDao) :  ScreenModel {
    private val _isLogout = MutableStateFlow(false)
    val isLogout: StateFlow<Boolean> = _isLogout

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun onLogoutSuccess() {
        _isLogout.value = false
    }

    init {
        loadData()
    }

    fun onToggleCountry(isVisible: Boolean) {
        _homeState.value = _homeState.value.copy(showCountrySheet = isVisible)
    }

    fun onSelectCountry(country : CountryModel) {
        _homeState.value = _homeState.value.copy(selectedCountry = country)
        screenModelScope.launch {
            countryDao.insertCountry(country.toEntity())
        }
    }

    private fun loadData() {
        screenModelScope.launch { // Use screenModelScope or viewModelScope
            // Call the function from the repository
            getDbCountries().collect { countryList ->
                // Update the StateFlow whenever new data is emitted
                if (countryList.isEmpty()) {
                    getCountries()
                } else {
                    _homeState.value = _homeState.value.copy(countryList = countryList)
                }
            }

            val selectedCountry = getSelectedCountry()
            selectedCountry?.let {
                _homeState.value = _homeState.value.copy(selectedCountry = it)
            } ?: run {
                _homeState.value = _homeState.value.copy(showCountrySheet = true)
            }
        }
        getCategories()
        getBrands()
        getBanners()
        getProducts()
    }


    private fun getCountries() = screenModelScope.launch {
        val request = ParamRequest(
            param = emptyList(),
        );
        val result = homeRepository.getCountries(request)
        _homeState.value = _homeState.value.copy(
            isLoading = false,
            success = true,
            countryList = result?.data?.dataList,
        )
        val countryEntities = result?.data?.dataList?.map { it.toEntity() }

        if (countryEntities != null) {
            countryDao.insertCountries(countryEntities)
        };
    }

    private fun getCategories() = screenModelScope.launch {
        val request = ParamRequest(
            param = emptyList(),
        );
        val result = homeRepository.getCategories(request)
        _homeState.value = _homeState.value.copy(
            isLoading = false,
            success = true,
            categoryList = result?.data,
            selectedCategory = result?.data?.first()
        )
    }

    private fun getBrands() = screenModelScope.launch {
        val request = ParamRequest(
            param = emptyList(),
        );
        val result = homeRepository.getBrands(request)
        _homeState.value = _homeState.value.copy(
            isLoading = false,
            success = true,
            brandList = result?.data?.dataList,
        )
    }

    private fun getBanners() = screenModelScope.launch {
        val request = ParamRequest(
            param = emptyList(),
        );
        val result = homeRepository.getBanners(request)
        _homeState.value = _homeState.value.copy(
            isLoading = false,
            success = true,
            bannerList = result?.data?.dataList,
        )
    }

    private fun getProducts() = screenModelScope.launch {
        val request = ProductRequestModel(
            param = listOf(
                ParamModel(
                    propertyName = "p_sortBy",
                    propertyValue = "new_arrival"
                ),
                ParamModel(
                    propertyName = "calling_from",
                    propertyValue = "ProductSearch"
                ),
                ParamModel(
                    propertyName = "country_code",
                    propertyValue = "238"
                ),
            ),
            offset = 0,
            limit = 10,
        );
        val result = homeRepository.getProducts(request)
        _homeState.value = _homeState.value.copy(
            isLoading = false,
            success = true,
            newArrivalProductsList = result?.data?.dataList,
        )
    }

    private fun getDbCountries(): Flow<List<CountryModel>> {
        return countryDao.getAllCountries()
            .map { entities ->
                entities.map { it.toModel() }
            }
    }

    private suspend fun getSelectedCountry(): CountryModel? {
        return countryDao.getSelectedCountry().firstOrNull()?.toModel()
    }
}