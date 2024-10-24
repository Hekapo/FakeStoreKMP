package com.fakestore.fakestorekmp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fakestore.fakestorekmp.ui.root.RootNavGraph
import com.fakestore.fakestorekmp.ui.root.RootNavigationComponent

@Composable
fun ContentView(component: RootNavigationComponent) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            RootNavGraph(component)
        }
    }
}
