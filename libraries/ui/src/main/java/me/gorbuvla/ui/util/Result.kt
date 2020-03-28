package me.gorbuvla.ui.util

/**
 * Result wrapper to handle operations in more concise way.
 * Introduced because kotlin [Result] can't be used as return type.
 */
@Suppress("ClassName") // lowercase type names are used to match Kotlin's upcoming type definition.
sealed class Result<out T> {
    data class success<out T>(val value: T) : Result<T>()
    data class failure(val error: Throwable) : Result<Nothing>()
}