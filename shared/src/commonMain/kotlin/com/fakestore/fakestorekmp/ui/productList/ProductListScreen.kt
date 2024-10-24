package com.fakestore.fakestorekmp.ui.productList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fakestore.fakestorekmp.domain.models.ProductModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProductListScreen(component: ProductListComponent) {
    val uiState by component.state.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> CircularProgressIndicator()
        else -> {
            Column {
                repeat(10) {
                    Text(text = "text $it")
                }

                Button(onClick = { component.onProductSelected(1) }) {
                    Text(text = "onProductSelected 1")
                }
            }
        }
    }
}

@Composable
private fun ProductItem(model: ProductModel) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder, // Heart icon
                    contentDescription = "Favorite",
                    tint = Color(0xFFAD7BFF) // Light purple color
                )
            }

//            Image(
//                painter = painterResource(id = R.drawable.sample_tshirt), // Replace with your image
//                contentDescription = "Product Image",
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(CircleShape)
//                    .border(2.dp, Color.White, CircleShape)
//            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Mens Casual Premium Slim Fit T-Shirts",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$ 22.3",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFAD7BFF) // Light purple color
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { /* Handle buy click */ },
            ) {
                Text(text = "Comprar", color = Color.White)
            }
        }
    }
}

@Composable
@Preview
private fun ProductItemPreview(modifier: Modifier = Modifier) {
    MaterialTheme {

    }
}
