package me.gorbuvla.articles.flow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import me.gorbuvla.core.model.ArticleRepository
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.into
import me.gorbuvla.ui.util.*

/**
 * ViewModel for screen with feed detail.
 */
class ArticleDetailViewModel(
    id: Int, repository: ArticleRepository
): ViewModel() {

    private val viewState = MutableLiveData<ViewState<Article>>()

    val state: LiveData<ViewState<Article>>
        get() = viewState

    val article: Article?
        get() = (state.value as? ViewState.Loaded)?.data

    init {
        viewState.loading()

        repository.article(id)
            .into(viewState)
            .launchIn(viewModelScope)
    }
}