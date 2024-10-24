package com.fakestore.fakestorekmp.data.network

internal object NetworkConstants {

    const val BASE_URL = "https://fakestoreapi.com/"

    object Products {
        const val ROUTE = "${BASE_URL}products"
        val byId: (Int) -> String = { id -> "$ROUTE/$id" }
    }
}
