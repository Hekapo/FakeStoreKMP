package com.fakestore.fakestorekmp.data.network.di

import com.fakestore.fakestorekmp.data.network.client.ProductClient
import com.fakestore.fakestorekmp.data.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module
    get() = { enableLogging ->
        module {
            single { createHttpClient(enableLogging) }
            single { ProductClient(httpClient = get()) }
        }
    }
