package com.fakestore.fakestorekmp.data.repository

import com.fakestore.fakestorekmp.data.mappers.ProductMapper
import com.fakestore.fakestorekmp.data.network.client.ProductClient
import com.fakestore.fakestorekmp.domain.models.ProductModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductsRepositoryImpl : ProductsRepository, KoinComponent {

    private val productClient by inject<ProductClient>()
    private val productMapper by inject<ProductMapper>()

    override suspend fun getProductList(): Result<List<ProductModel>> {
        return runCatching {
            productClient.getProductList()
        }.mapCatching(productMapper::map)
    }

    override suspend fun getProductDetailById(productId: Int): Result<ProductModel> {
        return runCatching {
            productClient.getProductById(productId)
        }.mapCatching(productMapper::map)
    }
}
