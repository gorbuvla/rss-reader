package me.gorbuvla.core

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.error
import me.gorbuvla.ui.util.loaded

/**
 * TODO add class description
 */
fun <T> Flow<T>.into(viewState: MutableLiveData<ViewState<T>>): Flow<T> {
    return onEach { viewState.loaded(it) }
        .catch { viewState.error(it) }
}

