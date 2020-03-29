package me.gorbuvla.articles.flow.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import me.gorbuvla.articles.model.ArticleRepository
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.into
import me.gorbuvla.ui.util.*

/**
 * ViewModel for screen with feed items.
 */
class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val viewState = MutableLiveData<ViewState<List<Article>>>()

    val state: LiveData<ViewState<List<Article>>>
        get() = viewState

    init {
        viewState.loading()

        loadLatest()

        repository.observeArticles()
            .into(viewState)
            .launchIn(viewModelScope)


            //.onEach { viewState.loaded(it) }
//            .catch { viewState.error(it) }
//            .launchIn(viewModelScope)
    }

    fun retry() {
        viewState.reloadingWithPrevious()
        loadLatest()
    }

    private fun loadLatest() {
        launch { repository.fetchArticles() }
    }
}