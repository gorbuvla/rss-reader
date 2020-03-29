package me.gorbuvla.core

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.error
import me.gorbuvla.ui.util.loaded
import timber.log.Timber

/**
 * TODO add class description
 */
suspend fun <T> Flow<T>.into(viewState: MutableLiveData<ViewState<T>>) {
    collect { viewState.loaded(it) }
}

