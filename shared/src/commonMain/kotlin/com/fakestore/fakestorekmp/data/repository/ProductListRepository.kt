package com.fakestore.fakestorekmp.data.repository

import com.fakestore.fakestorekmp.domain.models.ProductModel

interface ProductsRepository {
    suspend fun getProductList(): Result<List<ProductModel>>
    suspend fun getProductDetailById(productId: Int): Result<ProductModel>
}
