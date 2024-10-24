package com.fakestore.fakestorekmp.data.network.ext

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> executeAction(
    crossinline action: suspend () -> HttpResponse,
): T {
    return runCatching {
        action().body<T>()
    }.getOrElse {
        throw it
    }
}
