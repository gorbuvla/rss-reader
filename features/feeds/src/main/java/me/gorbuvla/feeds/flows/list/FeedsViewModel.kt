package me.gorbuvla.feeds.flows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import me.gorbuvla.core.domain.Feed
import me.gorbuvla.feeds.model.FeedRepository
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.launch
import java.net.URL

/**
 * ViewModel for feed list screen.
 */
class FeedsViewModel(private val repository: FeedRepository) : ViewModel() {

    val feeds: LiveData<ViewState<List<Feed>>> = repository.feeds().asLiveData().map { feeds ->
        if (feeds.isNotEmpty()) ViewState.Loaded(feeds) else ViewState.Empty
    }

    fun addNew(name: String, link: URL) {
        launch { repository.add(name, link) }
    }

    fun remove(feed: Feed) {
        launch { repository.remove(feed) }
    }
}