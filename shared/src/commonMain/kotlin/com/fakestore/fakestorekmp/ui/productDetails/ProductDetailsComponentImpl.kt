package com.fakestore.fakestorekmp.ui.productDetails

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.fakestore.fakestorekmp.ui.productDetails.store.ProductDetailsStore
import com.fakestore.fakestorekmp.ui.productDetails.store.ProductDetailsStoreFactory
import kotlinx.coroutines.flow.StateFlow

interface ProductDetailsComponent {
    val state: StateFlow<ProductDetailsStore.State>

    fun navigateUp()
}

internal class ProductDetailsComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    productId: Int,
    private val navigateUp: () -> Unit,
) : ProductDetailsComponent, ComponentContext by componentContext {

    private val productStore =
        instanceKeeper.getStore {
            ProductDetailsStoreFactory(
                storeFactory = storeFactory,
                productId = productId,
            ).create()
        }

    override val state: StateFlow<ProductDetailsStore.State> = productStore.stateFlow

    override fun navigateUp() = navigateUp.invoke()
}
