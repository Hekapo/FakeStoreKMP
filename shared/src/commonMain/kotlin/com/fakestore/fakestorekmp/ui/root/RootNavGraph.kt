package com.fakestore.fakestorekmp.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.fakestore.fakestorekmp.ui.productDetails.ProductDetailsScreen
import com.fakestore.fakestorekmp.ui.productList.ProductListScreen

@Composable
internal fun RootNavGraph(
    component: RootNavigationComponent
) {
    Children(
        stack = component.childStack,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is RootNavigationComponent.Child.ProductList -> ProductListScreen(child.component)
            is RootNavigationComponent.Child.ProductDetails -> ProductDetailsScreen(child.component)
        }
    }
}
