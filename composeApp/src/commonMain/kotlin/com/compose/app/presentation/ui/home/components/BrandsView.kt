package com.compose.app.presentation.ui.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.compose.app.data.model.home.BrandModel
import com.compose.app.data.remote.ApiRoutes
import getScreenSize

@Composable
fun BrandsView(
    brands: List<BrandModel>?,
    onBrandTap: (BrandModel) -> Unit
) {
    if (brands.isNullOrEmpty()) return

    Column {
        val screenWidth = getScreenSize().width
        val itemWidth = (screenWidth - 50.dp) / 4
        Text(modifier = Modifier.padding(horizontal = 10.dp), text = "Top Brands", fontSize = 14.sp, fontWeight = FontWeight.W600)
        LazyRow(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(brands) { brand ->
                Box(
                    modifier = Modifier
                        .width(itemWidth)
                        .border(width = 4.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = "${ApiRoutes.IMAGE_BASE_URL}${brand.image}",
                        contentDescription = "product",
                        modifier = Modifier.width(60.dp).height(60.dp).clip(
                            CircleShape
                        ),
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }
}