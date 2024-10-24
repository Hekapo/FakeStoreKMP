package com.fakestore.fakestorekmp.data.network.client

import com.fakestore.fakestorekmp.data.network.NetworkConstants
import com.fakestore.fakestorekmp.data.network.ext.executeAction
import com.fakestore.fakestorekmp.data.response.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ProductClient(
    private val httpClient: HttpClient,
) {

    suspend fun getProductList(): List<ProductResponse> {
        return executeAction {
            httpClient.get(NetworkConstants.Products.ROUTE)
        }
    }

    suspend fun getProductById(
        productId: Int,
    ): ProductResponse {
        return executeAction {
            httpClient.get(NetworkConstants.Products.byId(productId))
        }
    }
}
