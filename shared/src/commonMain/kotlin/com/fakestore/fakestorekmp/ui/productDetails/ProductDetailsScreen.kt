package com.fakestore.fakestorekmp.ui.productDetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProductDetailsScreen(component: ProductDetailsComponent) {
    val state by component.state.collectAsStateWithLifecycle()

    Text(text = state.product?.image.toString())

}
