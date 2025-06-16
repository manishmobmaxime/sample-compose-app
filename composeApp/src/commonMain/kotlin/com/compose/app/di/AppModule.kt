package com.compose.app.di

import com.compose.app.data.repository.AuthRepository
import com.compose.app.data.repository.AuthRepositoryImpl
import com.compose.app.presentation.ui.MainViewModel
import com.compose.app.presentation.ui.account.AccountViewModel
import com.compose.app.presentation.ui.home.HomeViewModel
import com.compose.app.presentation.ui.login.LoginViewModel
import com.compose.app.presentation.ui.splash.SplashViewModel
import org.koin.dsl.module

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    factory { SplashViewModel(get()) }
    factory { LoginViewModel(get()) }
    factory { HomeViewModel(get()) }
    factory { MainViewModel() }
    factory { AccountViewModel(get()) }
}