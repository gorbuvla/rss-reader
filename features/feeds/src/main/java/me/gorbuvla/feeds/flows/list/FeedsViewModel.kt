package me.gorbuvla.feeds.flows.list

import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.core.domain.Feed
import me.gorbuvla.feeds.model.FeedRepository
import me.gorbuvla.ui.util.*
import java.net.URL

/**
 * ViewModel for feed list screen.
 */
class FeedsViewModel(private val repository: FeedRepository) : ViewModel() {

    private val feedState = MutableLiveData<ViewState<List<Feed>>>()

    val feeds: LiveData<ViewState<List<Feed>>>
        get() = feedState

    init {
        feedState.loading()
        repository.feeds()
            .onEach { feedState.loaded(it) }
            .catch { feedState.error(it) }
            .launchIn(viewModelScope)
    }

    fun addNew(name: String, link: URL) {
        launch { repository.add(name, link) }
    }

    fun remove(feed: Feed) {
        launch { repository.remove(feed) }
    }
}