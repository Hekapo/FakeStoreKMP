package com.fakestore.fakestorekmp.ui.productDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.fakestore.fakestorekmp.core.ui.RatingComponent
import com.fakestore.fakestorekmp.domain.models.ProductModel

@Composable
internal fun ProductDetailsScreen(component: ProductDetailsComponent) {
    val uiState by component.state.collectAsStateWithLifecycle()

    val productModel = uiState.product

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.isLoading -> CircularProgressIndicator()

            productModel != null && !uiState.isLoading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    ProductDetailHeader(
                        model = productModel,
                        navigateUp = component::navigateUp,
                    )

                    productModel.title?.let { title ->
                        Text(
                            modifier = Modifier.padding(top = 32.dp),
                            text = title,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    ProductDetailInfo(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        model = productModel,
                    )

                    productModel.description?.let { description ->
                        Text(
                            modifier = Modifier.padding(top = 32.dp),
                            text = "Description",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = description,
                            fontSize = 17.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Light,
                        )
                    }
                }
            }

            uiState.error != null && !uiState.isLoading -> {
                Text(text = uiState.error ?: "Error")
            }
        }
    }
}

@Composable
private fun ProductDetailHeader(
    modifier: Modifier = Modifier,
    model: ProductModel,
    navigateUp: () -> Unit,
) {
    val context = LocalPlatformContext.current

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "NavigateBack",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Icon(
                    modifier = Modifier.padding(end = 12.dp, top = 12.dp),
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(model.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(12.dp)
            )
        }
    }
}

@Composable
private fun ProductDetailInfo(modifier: Modifier = Modifier, model: ProductModel) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        model.price?.let {
            InfoRow(label = "Price") {
                Text(
                    text = "${model.price}",
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        model.rating?.rate?.let { rate ->
            InfoRow(label = "Rating") {
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "$rate",
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.SemiBold,
                    )
                    RatingComponent(rating = rate)
                }
            }
        }

        model.rating?.count?.let { count ->
            InfoRow(label = "Reviews") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = count,
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.SemiBold,
        )

        content()
    }
}
