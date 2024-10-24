package com.fakestore.fakestorekmp.data.mappers

import com.fakestore.fakestorekmp.data.response.ProductResponse
import com.fakestore.fakestorekmp.domain.models.ProductModel

class ProductMapper {
    fun map(response: ProductResponse): ProductModel {
        return with(response) {
            ProductModel(
                id = id,
                title = title,
                price = price,
                category = category,
                description = description,
                image = image,
            )
        }
    }

    fun map(response: List<ProductResponse>): List<ProductModel> = response.map(::map)
}
