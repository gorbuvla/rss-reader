package me.gorbuvla.articles.flow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.articles.model.ArticleRepository
import me.gorbuvla.core.domain.Article
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.error
import me.gorbuvla.ui.util.loaded
import me.gorbuvla.ui.util.loading

/**
 * ViewModel for screen with feed detail.
 */
class ArticleDetailViewModel(
    id: Long,
    repository: ArticleRepository
): ViewModel() {

    private val viewState = MutableLiveData<ViewState<Article>>()

    val state: LiveData<ViewState<Article>>
        get() = viewState

    val textToShare: String?
        get() = (state.value as? ViewState.Loaded)?.data?.toString()

    init {
        viewState.loading()

        repository.observeArticle(id)
            .onEach { viewState.loaded(it) }
            .catch { viewState.error(it) }
            .launchIn(viewModelScope)
    }
}