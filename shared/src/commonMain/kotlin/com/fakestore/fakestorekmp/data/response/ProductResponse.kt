package com.fakestore.fakestorekmp.data.response

import kotlinx.serialization.Serializable

@Serializable
class ProductResponse(
    val id: Int? = null,
    val title: String? = null,
    val price: Double? = null,
    val category: String? = null,
    val description: String? = null,
    val image: String? = null,
)
