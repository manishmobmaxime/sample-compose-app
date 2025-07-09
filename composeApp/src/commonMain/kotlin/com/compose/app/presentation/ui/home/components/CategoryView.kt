package com.compose.app.presentation.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.compose.app.data.model.category.ProductCategoryModel
import com.compose.app.data.remote.ApiRoutes
import getScreenSize

@Composable
fun CategoryView(
    categories: List<ProductCategoryModel>?,
    onCategoryTap: (ProductCategoryModel) -> Unit
) {
    if (categories.isNullOrEmpty()) return
    val screenWidth = getScreenSize().width;
    val itemWidth = (screenWidth - 60.dp) / 5
    LazyRow(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(categories) { category ->
            Column(
                modifier = Modifier.width(itemWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = "${ApiRoutes.IMAGE_BASE_URL}${category.image}",
                    contentDescription = "product",
                    modifier = Modifier.width(60.dp).height(60.dp).clip(
                        CircleShape
                    ).clickable {
                        onCategoryTap(category)
                    },
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = category.productCategoryName ?: "",
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