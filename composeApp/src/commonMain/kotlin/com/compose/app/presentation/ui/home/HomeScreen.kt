package com.compose.kotlin


import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.compose.kotlin.data.repository.AuthRepositoryImpl
import com.compose.kotlin.presentation.ui.home.HomeViewModel
import com.compose.kotlin.presentation.ui.login.LoginViewModel

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = remember {
            HomeViewModel(AuthRepositoryImpl(), navigator)
        }
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text("Welcome!")
            Button(onClick = {viewModel.logout()}) {
                Text("Logout")
            }
        }
    }
}


//@Composable
//fun HomeScreen(navController: NavHostController) {
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                modifier = Modifier.padding(WindowInsets.safeDrawing.asPaddingValues()),
//                title = { Text("Home Screen") },
//                backgroundColor = MaterialTheme.colors.primary,
//                elevation = 4.dp,
//                contentColor = Color.White,
//            )
//        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .padding(WindowInsets.safeDrawing.asPaddingValues())
//                    .padding(16.dp)
//                    .fillMaxSize()
//            ) {
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Button(onClick = {
//                    navController.navigate(Routes.PROFILE);
//                }, modifier = Modifier.fillMaxWidth()) {
//                    Text("Go to Profile")
//                }
//            }
//        }
//    )
//}