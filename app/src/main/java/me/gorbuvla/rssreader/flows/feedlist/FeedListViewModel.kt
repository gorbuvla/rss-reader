package me.gorbuvla.rssreader.flows.feedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.gorbuvla.rssreader.base.ViewState
import me.gorbuvla.rssreader.base.error
import me.gorbuvla.rssreader.base.launch
import me.gorbuvla.rssreader.base.loaded
import me.gorbuvla.rssreader.base.loading
import me.gorbuvla.rssreader.base.wrapResult
import me.gorbuvla.rssreader.model.FeedRepository
import me.gorbuvla.rssreader.model.domain.FeedItem
import me.gorbuvla.rssreader.util.Result

/**
 * ViewModel for screen with feed items.
 */
class FeedListViewModel(private val repository: FeedRepository) : ViewModel() {

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