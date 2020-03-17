package me.gorbuvla.rssreader.base

import androidx.lifecycle.MutableLiveData

/**
 * Base state that can be maintained by any view model
 */
sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Error(val error: Throwable) : ViewState<Nothing>()
    data class Loaded<out T>(val data: T) : ViewState<T>()
    data class Reloading<out T>(val previousData: T?) : ViewState<T>()
}

/**
 * Extensions on [MutableLiveData] to reduce boilerplate code
 */

fun <T> MutableLiveData<ViewState<T>>.loading() {
    value = ViewState.Loading
}

fun <T> MutableLiveData<ViewState<T>>.reloadingWithPrevious() {
    val previous = (value as? ViewState.Loaded)?.data
    value = ViewState.Reloading(previous)
}

fun <T> MutableLiveData<ViewState<T>>.reloading(previous: T? = null) {
    value = ViewState.Reloading(previous)
}

fun <T> MutableLiveData<ViewState<T>>.loaded(data: T) {
    value = ViewState.Loaded(data)
}

fun MutableLiveData<ViewState<Unit>>.loaded() {
    value = ViewState.Loaded(Unit)
}

fun <T> MutableLiveData<ViewState<T>>.error(err: Throwable) {
    value = ViewState.Error(err)
}