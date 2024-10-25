package com.fakestore.fakestorekmp.ui.productDetails.store

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

internal class ProductDetailsStoreFactory(
    private val storeFactory: StoreFactory,
    private val productId: Int,
) : KoinComponent {

    private val productsRepository by inject<ProductsRepository>()

    fun create(): ProductDetailsStore = object : ProductDetailsStore,
        Store<ProductDetailsStore.Intent, ProductDetailsStore.State, Nothing> by storeFactory.create(
            name = "ProductDetailStore",
            initialState = ProductDetailsStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    private sealed interface Message {
        data object ProductDetailLoading : Message
        data class ProductDetailLoaded(val product: ProductModel) : Message
        data class ProductDetailFailed(val error: String?) : Message
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<ProductDetailsStore.Intent, Unit, ProductDetailsStore.State, Message, Nothing>() {
        override fun executeAction(action: Unit) = getProductDetail(productId)

        override fun executeIntent(intent: ProductDetailsStore.Intent) = Unit

        private var getProductDetailJob: Job? = null
        private fun getProductDetail(id: Int) {
            if (getProductDetailJob?.isActive == true) return

            getProductDetailJob = scope.launch {
                dispatch(Message.ProductDetailLoading)

                productsRepository.getProductDetailById(id)
                    .onSuccess { product ->
                        dispatch(Message.ProductDetailLoaded(product))
                    }.onFailure {
                        dispatch(Message.ProductDetailFailed(it.message))
                    }
            }
        }

        override fun dispose() {
            getProductDetailJob?.cancel()
            super.dispose()
        }
    }

    private object ReducerImpl : Reducer<ProductDetailsStore.State, Message> {
        override fun ProductDetailsStore.State.reduce(msg: Message): ProductDetailsStore.State =
            when (msg) {
                is Message.ProductDetailLoading -> copy(isLoading = true)
                is Message.ProductDetailLoaded -> ProductDetailsStore.State(
                    product = msg.product,
                    isLoading = false,
                )

                is Message.ProductDetailFailed -> copy(error = msg.error, isLoading = false)
            }
    }
}
