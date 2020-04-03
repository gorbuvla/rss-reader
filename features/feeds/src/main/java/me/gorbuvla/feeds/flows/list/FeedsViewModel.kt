package me.gorbuvla.feeds.flows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import me.gorbuvla.core.domain.Feed
import me.gorbuvla.feeds.model.FeedRepository
import me.gorbuvla.ui.util.ViewState

/**
 * ViewModel for feed list screen.
 */
class FeedsViewModel(private val repository: FeedRepository) : ViewModel() {

    val feeds: LiveData<ViewState<List<Feed>>>
        get() = repository.feeds().asLiveData().map { ViewState.Loaded(it) }
}