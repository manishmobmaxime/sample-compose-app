package com.compose.app.presentation.ui.home


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.compose.app.data.model.home.ProductModel
import com.compose.app.presentation.components.CustomAppBar
import com.compose.app.presentation.ui.login.LoginScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.koinNavigatorScreenModel<HomeViewModel>()
        val isLogout by viewModel.isLogout.collectAsState()

        val productsState by viewModel.productsState.collectAsState()

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
                CustomAppBar(
                    title = "Home",
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(top = paddingValues.calculateTopPadding())
                ) {
                    Text(
                        text = "Products",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(10.dp)
                    )
                    Box(modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 15.dp)) {
                        when (productsState) {
                            is ProductListState.Loading -> {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator() // Show a loading spinner
                                }
                            }
                            is ProductListState.Success -> {
                                val products = (productsState as ProductListState.Success).products
                                if (products != null) {
                                    if(products.isNotEmpty()) {
                                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                                            items(products) { product ->
                                                ProductListItem(product)
                                            }
                                        }
                                    }
                                }
                            }
                            is ProductListState.Error -> {
                                val message = (productsState as ProductListState.Error).message
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text("Error: $message") // Show error message
                                }
                            }
                            ProductListState.Idle -> {
                                // Optionally show a message or nothing if it's idle
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text("Waiting to load products.")
                                }
                            }
                        }
                    }
                }
            }
        )
    }

    @Composable
    fun ProductListItem(product: ProductModel,  modifier: Modifier = Modifier) {
        // Re-use or define your product list item UI here
        Box(modifier =  Modifier
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                AsyncImage(
                    model = product.image, // Example URL
                    contentDescription = "product",
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentScale = ContentScale.Fit,
                    // Optional: Placeholder and error drawables (you'd need to provide these in resources)
                    // placeholder = painterResource(R.drawable.placeholder_image),
                    // error = painterResource(R.drawable.error_image)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Price: $${product.price}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Rating: ${product.rating.rate} (${product.rating.count} reviews)", style = MaterialTheme.typography.bodySmall)
                // Add AsyncImage for product.image if you're using Coil/Glide/etc.
            }
        }
    }
}