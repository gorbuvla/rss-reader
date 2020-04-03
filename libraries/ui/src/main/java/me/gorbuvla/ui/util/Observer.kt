package me.gorbuvla.ui.util

import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * TODO add class description
 */

@Composable
fun <T> observe(data: LiveData<T>): T? {
    var result by state { data.value }
    val observer = remember { Observer<T> { result = it } }

    onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    return result
}