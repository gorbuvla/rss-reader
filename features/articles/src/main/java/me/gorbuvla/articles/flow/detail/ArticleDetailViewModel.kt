package me.gorbuvla.articles.flow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.gorbuvla.core.model.ArticleRepository
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.into
import me.gorbuvla.ui.util.*

/**
 * ViewModel for screen with feed detail.
 */
class ArticleDetailViewModel(
    id: Int,
    repository: ArticleRepository
): ViewModel() {

    private val viewState = MutableLiveData<ViewState<Article>>()

    val state: LiveData<ViewState<Article>>
        get() = viewState

    val textToShare: String?
        get() = (state.value as? ViewState.Loaded)?.data?.toString()

    init {
        viewState.loading()

        launch {
            repository.article(id)
                .into(viewState)
        }
    }
}