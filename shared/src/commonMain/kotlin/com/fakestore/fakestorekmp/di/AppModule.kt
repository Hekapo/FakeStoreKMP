package com.fakestore.fakestorekmp.di

import com.fakestore.fakestorekmp.data.di.dataModule
import com.fakestore.fakestorekmp.data.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule(enableNetworkLogs),
            dataModule,
        )
    }