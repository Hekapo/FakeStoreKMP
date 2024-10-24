package com.fakestore.fakestorekmp.ui.productList.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.fakestore.fakestorekmp.data.repository.ProductsRepository
import com.fakestore.fakestorekmp.domain.models.ProductModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class   ProductListStoreFactory(
    private val storeFactory: StoreFactory,
) : KoinComponent {

    private val productsRepository by inject<ProductsRepository>()

    fun create(): ProductListStore = object : ProductListStore,
        Store<ProductListStore.Intent, ProductListStore.State, Nothing> by storeFactory.create(
            name = "ProductListStore",
            initialState = ProductListStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    private sealed interface Message {
        data object ProductListLoading : Message
        data class ProductListLoaded(val products: List<ProductModel>) : Message
        data class ProductListFailed(val error: String?) : Message
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<ProductListStore.Intent, Unit, ProductListStore.State, Message, Nothing>() {
        override fun executeAction(action: Unit) = getProductList()

        override fun executeIntent(intent: ProductListStore.Intent) = Unit

        private var getProductListJob: Job? = null
        private fun getProductList() {
            if (getProductListJob?.isActive == true) return

            getProductListJob = scope.launch {
                dispatch(Message.ProductListLoading)

                productsRepository.getProductList()
                    .onSuccess { products ->
                        dispatch(Message.ProductListLoaded(products))
                    }.onFailure {
                        dispatch(Message.ProductListFailed(it.message))
                    }
            }
        }
    }

    private object ReducerImpl : Reducer<ProductListStore.State, Message> {
        override fun ProductListStore.State.reduce(msg: Message): ProductListStore.State =
            when (msg) {
                is Message.ProductListLoading -> copy(isLoading = true)
                is Message.ProductListLoaded -> ProductListStore.State(
                    products = msg.products,
                    isLoading = false,
                )

                is Message.ProductListFailed -> copy(error = msg.error, isLoading = false)
            }
    }
}
