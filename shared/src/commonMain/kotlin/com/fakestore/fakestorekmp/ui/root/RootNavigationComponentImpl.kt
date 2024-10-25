package com.fakestore.fakestorekmp.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.fakestore.fakestorekmp.ui.productDetails.ProductDetailsComponent
import com.fakestore.fakestorekmp.ui.productDetails.ProductDetailsComponentImpl
import com.fakestore.fakestorekmp.ui.productList.ProductListComponent
import com.fakestore.fakestorekmp.ui.productList.ProductListComponentImpl
import kotlinx.serialization.Serializable

interface RootNavigationComponent : DecomposeComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class ProductList(val component: ProductListComponent) : Child
        data class ProductDetails(val component: ProductDetailsComponent) : Child
    }
}

class RootNavigationComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
) : RootNavigationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ScreenConfig>()

    override val childStack: Value<ChildStack<*, RootNavigationComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ScreenConfig.ProductList,
        handleBackButton = true,
        serializer = ScreenConfig.serializer(),
        childFactory = ::createChild
    )

    private fun createChild(
        config: ScreenConfig,
        componentContext: ComponentContext,
    ): RootNavigationComponent.Child {
        return when (config) {
            is ScreenConfig.ProductList -> RootNavigationComponent.Child.ProductList(
                component = ProductListComponentImpl(
                    componentContext = componentContext,
                    storeFactory = storeFactory,
                    onProductSelected = { navigation.push(ScreenConfig.ProductDetails(it)) },
                )
            )

            is ScreenConfig.ProductDetails -> RootNavigationComponent.Child.ProductDetails(
                component = ProductDetailsComponentImpl(
                    componentContext = componentContext,
                    storeFactory = storeFactory,
                    productId = config.productId,
                    navigateUp = navigation::pop,
                )
            )
        }
    }

    @Serializable
    private sealed interface ScreenConfig {

        @Serializable
        data object ProductList : ScreenConfig

        @Serializable
        data class ProductDetails(val productId: Int) : ScreenConfig
    }
}
