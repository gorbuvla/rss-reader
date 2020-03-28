package me.gorbuvla.rssreader.flows.feeddetail

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
import me.gorbuvla.rssreader.model.domain.FeedDetail
import me.gorbuvla.rssreader.util.Result

/**
 * ViewModel for screen with feed detail.
 */
class FeedDetailViewModel(
    private val id: Int,
    private val repository: FeedRepository
): ViewModel() {

    private val viewState = MutableLiveData<ViewState<FeedDetail>>()

    val state: LiveData<ViewState<FeedDetail>>
        get() = viewState

    val textToShare: String?
        get() = (state.value as? ViewState.Loaded)?.data?.toString()

    init {
        loadDetail()
    }

    fun retry() {
        loadDetail()
    }

    private fun loadDetail() {
        viewState.loading()
        launch {
            when (val result = wrapResult { repository.fetchDetail(id) }) {
                is Result.success -> viewState.loaded(result.value)
                is Result.failure -> viewState.error(result.error)
            }
        }
    }
}