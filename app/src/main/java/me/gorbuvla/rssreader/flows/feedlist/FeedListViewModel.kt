package me.gorbuvla.rssreader.flows.feedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import me.gorbuvla.rssreader.base.*
import me.gorbuvla.rssreader.model.FeedRepository
import me.gorbuvla.rssreader.model.domain.FeedItem
import me.gorbuvla.rssreader.util.Result

/**
 * ViewModel for screen with feed items.
 */
class FeedListViewModel(private val repository: FeedRepository): BaseViewModel() {

    private val viewState = MutableLiveData<ViewState<List<FeedItem>>>()

    val state: LiveData<ViewState<List<FeedItem>>>
        get() = viewState

    init {
        loadLatest()
    }

    fun retry() {
        loadLatest()
    }

    private fun loadLatest() {
        viewState.loading()
        launch {
            when (val result = wrapResult { repository.fetchFeed() }) {
                is Result.success -> viewState.loaded(result.value)
                is Result.failure -> viewState.error(result.error)
            }
        }
    }
}