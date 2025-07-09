package com.compose.app.presentation.ui.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.compose.app.data.model.home.ProductModel
import com.compose.app.data.remote.ApiRoutes
import getScreenSize

@Composable
fun ProductListView(
    products: List<ProductModel>?,
    onProductTap: (ProductModel) -> Unit
) {
    if (products.isNullOrEmpty()) return

    Column {
        val screenWidth = getScreenSize().width
        val itemWidth = (screenWidth - 30.dp) / 2
        Text(modifier = Modifier.padding(horizontal = 10.dp), text = "New Arrival Products", fontSize = 14.sp, fontWeight = FontWeight.W600)
        LazyRow(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(products) { product ->
                Column(
                    modifier = Modifier
                        .width(itemWidth)
                        .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .clickable { onProductTap(product) }
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "${ApiRoutes.IMAGE_BASE_URL}${product.image}",
                        contentDescription = "product",
                        modifier = Modifier.width(120.dp).height(120.dp),
                        contentScale = ContentScale.Fit,
                    )
                    Column(modifier = Modifier.padding(top = 8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = product.productName ?: "",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W600),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis)
                        Text(text = "HSN Code: ${product.hsnCode}",
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W500, color = MaterialTheme.colorScheme.primary),)
                        Text(
                            buildAnnotatedString {
                                append("${product.salesPrice}/")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
                                    append(product.productPackingType)
                                }
                            }, style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W500)
                        )
                        Text(text = "(${product.packsPerPackage}/${product.productPackingType})",
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W500,))
                    }
                }
            }
        }
    }
}