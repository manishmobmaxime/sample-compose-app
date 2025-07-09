package com.compose.app.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compose.app.presentation.components.CountryPickerBottomSheet
import com.compose.app.presentation.ui.home.components.BannerView
import com.compose.app.presentation.ui.home.components.BrandsView
import com.compose.app.presentation.ui.home.components.CategoryView
import com.compose.app.presentation.ui.home.components.ProductListView
import com.compose.app.presentation.ui.home.components.TopSearchBar
import com.compose.app.presentation.ui.login.LoginScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.koinNavigatorScreenModel<HomeViewModel>()
        val isLogout by viewModel.isLogout.collectAsState()

        val homeState by viewModel.homeState.collectAsState()

        var searchQuery by remember { mutableStateOf("") }
        var showCountrySheet by remember { mutableStateOf(false) }

        val scrollState = rememberScrollState()

        LaunchedEffect(isLogout) {
            isLogout.let {
                if (it) {
                    navigator.replaceAll(LoginScreen())
                    viewModel.onLogoutSuccess()
                }
            }
        }
        Scaffold(
            topBar = {
                TopSearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { searchQuery = it },
                    onClearClick = { searchQuery = "" },
                    onMicClick = {  },
                    onCountryClick = { showCountrySheet = true }
                )
            },
            content = { paddingValues ->
                Column(modifier = Modifier.fillMaxSize().padding(top = paddingValues.calculateTopPadding())) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (homeState.isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator() // Show a loading spinner
                            }
                        } else {
                            Column(modifier = Modifier.verticalScroll(scrollState)) {
                                val categories = homeState.categoryList;
                                val brands = homeState.brandList;
                                val banners = homeState.bannerList;
                                val newArrivalProductsList = homeState.newArrivalProductsList;
                                CategoryView(
                                    categories = categories,
                                    onCategoryTap = {

                                    }
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                BannerView(
                                    productBanners = banners,
                                    onBannerTap = {

                                    }
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                BrandsView(
                                    brands = brands,
                                    onBrandTap =  {

                                    }
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                ProductListView(
                                    products = newArrivalProductsList,
                                    onProductTap =  {

                                    }
                                )
                            }
                        }
                    }
                }
                if (showCountrySheet) {
                    homeState.countryList?.let { countryList ->
                        CountryPickerBottomSheet(
                            countries = countryList,
                            onSelect = {
                                homeState.selectedCountry = it
                                showCountrySheet = false
                            },
                            onDismiss = {
                                showCountrySheet = false
                            }
                        )
                    }
                }
            }
        )
    }
}
