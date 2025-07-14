package com.compose.app.di

import com.compose.app.data.repository.AuthRepository
import com.compose.app.data.repository.AuthRepositoryImpl
import com.compose.app.data.repository.CategoryRepository
import com.compose.app.data.repository.CategoryRepositoryImpl
import com.compose.app.data.repository.HomeRepository
import com.compose.app.data.repository.HomeRepositoryImpl
import com.compose.app.presentation.ui.MainViewModel
import com.compose.app.presentation.ui.account.AccountViewModel
import com.compose.app.presentation.ui.cart.CartViewModel
import com.compose.app.presentation.ui.category.CategoryViewModel
import com.compose.app.presentation.ui.home.HomeViewModel
import com.compose.app.presentation.ui.login.LoginViewModel
import com.compose.app.presentation.ui.splash.SplashViewModel
import org.koin.dsl.module

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    factory { SplashViewModel(get()) }
    factory { LoginViewModel(get(), get()) }
    factory { MainViewModel() }
    factory { HomeViewModel(get(), get()) }
    factory { CategoryViewModel(get()) }
    factory { CartViewModel() }
    factory { AccountViewModel(get(), get()) }
}