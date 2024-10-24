package com.fakestore.fakestorekmp.ui.productDetails.store

import com.arkivanov.mvikotlin.core.store.Store
import com.fakestore.fakestorekmp.domain.models.ProductModel

interface ProductDetailsStore :
    Store<ProductDetailsStore.Intent, ProductDetailsStore.State, Nothing> {

    sealed interface Intent

    data class State(
        val product: ProductModel? = null,
        val isLoading: Boolean = true,
        val error: String? = null,
    )
}
