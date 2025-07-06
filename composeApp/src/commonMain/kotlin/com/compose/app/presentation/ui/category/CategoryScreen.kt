package com.compose.app.presentation.ui.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.compose.app.data.remote.ApiRoutes
import com.compose.app.presentation.components.CustomAppBar

class CategoryScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = navigator.koinNavigatorScreenModel<CategoryViewModel>()

        val categoryState by viewModel.categoryState.collectAsState()

        Scaffold(
            topBar = {
                CustomAppBar(
                    title = "Category",
                )
            },
            content = { paddingValues ->
                Column(modifier = Modifier.fillMaxSize().padding(top = paddingValues.calculateTopPadding())) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if(categoryState.isLoading) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator() // Show a loading spinner
                            }
                        }
                        if(categoryState.success) {
                            val categories = categoryState.categoryList;
                            if(!categories.isNullOrEmpty()) {
                                Row(modifier = Modifier.fillMaxSize()) {
                                    LazyColumn(modifier = Modifier.width(100.dp).fillMaxHeight()) {
                                        items(categories) { category ->
                                            val isSelected = category == categoryState.selectedCategory;
                                            Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max) .clickable { viewModel.setCategory(category) }.background(color = if (isSelected) Color.Transparent else MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)), verticalAlignment = Alignment.CenterVertically) {
                                                Box(modifier = Modifier.width(4.dp).fillMaxHeight().background(color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent))
                                                Text(
                                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 15.dp),
                                                    text = category.productCategoryName ?: "",
                                                    fontSize = 12.sp,
                                                    lineHeight = 16.sp,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                    }
                                    Box(modifier = Modifier.fillMaxHeight().padding(horizontal = 10.dp, vertical = 15.dp)) {
                                        categoryState.selectedCategory?.productSubCategory?.let {  subCategories ->
                                            LazyVerticalGrid(
                                                columns = GridCells.Fixed(3),
                                                verticalArrangement = Arrangement.spacedBy(15.dp),
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                modifier = Modifier.fillMaxSize()
                                            ) {
                                                items(subCategories) { subCategory ->
                                                    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                                        AsyncImage(
                                                            model = "${ApiRoutes.IMAGE_BASE_URL}${subCategory.image}",
                                                            contentDescription = "product",
                                                            modifier = Modifier.width(60.dp).height(60.dp).clip(
                                                                CircleShape),
                                                            contentScale = ContentScale.Fit,

                                                        )
                                                        Spacer(modifier = Modifier.height(6.dp))
                                                        Text(
                                                            text = subCategory.productSubCategoryName ?: "",
                                                            fontSize = 10.sp,
                                                            lineHeight = 12.sp,
                                                            fontWeight = FontWeight.W600,
                                                            textAlign = TextAlign.Center,
                                                            maxLines = 2,
                                                            overflow = TextOverflow.Ellipsis
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}