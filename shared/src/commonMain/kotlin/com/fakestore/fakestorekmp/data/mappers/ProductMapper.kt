package com.fakestore.fakestorekmp.data.mappers

import com.fakestore.fakestorekmp.data.response.ProductResponse
import com.fakestore.fakestorekmp.domain.models.ProductModel
import com.fakestore.fakestorekmp.domain.models.RatingModel

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
                rating = RatingModel(
                    rate = response.rating?.rate,
                    count = response.rating?.count,
                )
            )
        }
    }

    fun map(response: List<ProductResponse>): List<ProductModel> = response.map(::map)
}
