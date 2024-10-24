package com.fakestore.fakestorekmp.ui.productList.store

import com.arkivanov.mvikotlin.core.store.Store
import com.fakestore.fakestorekmp.domain.models.ProductModel

interface ProductListStore : Store<ProductListStore.Intent, ProductListStore.State, Nothing> {

    sealed interface Intent

    data class State(
        val products: List<ProductModel> = emptyList(),
        val isLoading: Boolean = true,
        val error: String? = null,
    )
}
