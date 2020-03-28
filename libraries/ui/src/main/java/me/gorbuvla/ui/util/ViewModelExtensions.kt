package me.gorbuvla.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * ViewModel convenience extensions.
 */

/**
 * Helper to wrap suspending calls into [Result]
 */
suspend fun <T> wrapResult(operation: suspend () -> T): Result<T> {
    return try {
        Result.success(operation())
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

/**
 * Shortcut for [viewModelScope.launch]
 */
fun ViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(context, start, block)
}
