package com.compose.kotlin.di

import com.compose.kotlin.data.repository.AuthRepository
import com.compose.kotlin.data.repository.AuthRepositoryImpl
import com.compose.app.presentation.ui.home.HomeViewModel
import com.compose.app.presentation.ui.login.LoginViewModel
import com.compose.app.presentation.ui.splash.SplashViewModel
import org.koin.dsl.module

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }

    factory { SplashViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { HomeViewModel(get()) }
}