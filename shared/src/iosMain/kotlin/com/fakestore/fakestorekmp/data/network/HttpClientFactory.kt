package com.fakestore.fakestorekmp.data.network

import io.ktor.client.HttpClient

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient()
}
