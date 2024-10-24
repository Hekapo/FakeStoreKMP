package com.fakestore.fakestorekmp.ui.productList

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.fakestore.fakestorekmp.ui.productList.store.ProductListStore
import com.fakestore.fakestorekmp.ui.productList.store.ProductListStoreFactory
import kotlinx.coroutines.flow.StateFlow

interface ProductListComponent {
    val state: StateFlow<ProductListStore.State>

    val onProductSelected: (Int) -> Unit
}

class ProductListComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    override val onProductSelected: (Int) -> Unit,
) : ProductListComponent, ComponentContext by componentContext {

    private val productsStore =
        instanceKeeper.getStore {
            ProductListStoreFactory(
                storeFactory = storeFactory,
            ).create()
        }

    override val state: StateFlow<ProductListStore.State> = productsStore.stateFlow
}
