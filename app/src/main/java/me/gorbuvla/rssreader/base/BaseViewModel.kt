package me.gorbuvla.rssreader.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import me.gorbuvla.rssreader.util.Result
import kotlin.coroutines.CoroutineContext

/**
 * Base ViewModel with convenience extensions.
 */
abstract class BaseViewModel: ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext

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
}