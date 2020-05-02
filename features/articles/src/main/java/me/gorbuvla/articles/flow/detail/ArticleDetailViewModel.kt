package me.gorbuvla.articles.flow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.model.ArticleRepository
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.error
import me.gorbuvla.ui.util.loaded
import me.gorbuvla.ui.util.loading

/**
 * ViewModel for screen with feed detail.
 */
class ArticleDetailViewModel(
    id: String, repository: ArticleRepository
): ViewModel() {

    private val viewState = MutableLiveData<ViewState<Article>>()

    val state: LiveData<ViewState<Article>>
        get() = viewState

    val article: Article?
        get() = (state.value as? ViewState.Loaded)?.data

    init {
        viewState.loading()

        repository.article(id)
            .onEach { viewState.loaded(it) }
            .catch { viewState.error(it) }
            .launchIn(viewModelScope)
    }
}