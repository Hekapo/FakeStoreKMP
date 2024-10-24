package com.fakestore.fakestorekmp.data.di

import com.fakestore.fakestorekmp.data.mappers.ProductMapper
import com.fakestore.fakestorekmp.data.repository.ProductsRepository
import com.fakestore.fakestorekmp.data.repository.ProductsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<ProductsRepository> { ProductsRepositoryImpl() }

    single { ProductMapper() }
}
