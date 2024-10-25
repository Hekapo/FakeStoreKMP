package com.fakestore.fakestorekmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.fakestore.fakestorekmp.di.initKoin
import com.fakestore.fakestorekmp.ui.ContentView
import com.fakestore.fakestorekmp.ui.root.RootNavigationComponentImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin(enableNetworkLogs = BuildConfig.DEBUG) {
            androidContext(applicationContext)
        }

        val rootComponent = RootNavigationComponentImpl(
            componentContext = defaultComponentContext(),
            storeFactory = DefaultStoreFactory(),
        )

        setContent {
            ContentView(component = rootComponent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}
