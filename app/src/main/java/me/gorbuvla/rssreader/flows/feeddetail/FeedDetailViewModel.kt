package me.gorbuvla.rssreader.flows.feeddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import me.gorbuvla.rssreader.base.*
import me.gorbuvla.rssreader.model.FeedRepository
import me.gorbuvla.rssreader.model.domain.FeedDetail
import me.gorbuvla.rssreader.util.Result

/**
 * ViewModel for screen with feed detail.
 */
class FeedDetailViewModel(
    private val id: Int,
    private val repository: FeedRepository
): BaseViewModel() {

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