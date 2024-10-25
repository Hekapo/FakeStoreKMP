package com.fakestore.fakestorekmp.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Int? = null,
    val title: String? = null,
    val price: Double? = null,
    val category: String? = null,
    val description: String? = null,
    val image: String? = null,
    val rating: RatingDto? = null
)

@Serializable
data class RatingDto(
    val rate: Double? = null,
    val count: String? = null,
)
