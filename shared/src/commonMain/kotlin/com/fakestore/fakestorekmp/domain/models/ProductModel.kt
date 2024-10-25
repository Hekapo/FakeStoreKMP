package com.fakestore.fakestorekmp.domain.models

data class ProductModel(
    val id: Int? = null,
    val title: String? = null,
    val price: Double? = null,
    val category: String? = null,
    val description: String? = null,
    val image: String? = null,
    val rating: RatingModel? = null,
)

data class RatingModel(
    val rate: Double? = null,
    val count: String? = null,
)
