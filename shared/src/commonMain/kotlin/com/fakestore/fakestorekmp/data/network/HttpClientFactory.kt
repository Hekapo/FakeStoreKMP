package com.fakestore.fakestorekmp.data.network

import io.ktor.client.HttpClient

expect fun createPlatformHttpClient(): HttpClient
