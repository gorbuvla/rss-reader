package me.gorbuvla.articles.flow.list

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import me.gorbuvla.core.model.ArticleRepository
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.ui.util.*

/**
 * ViewModel for screen with feed items.
 * TODO: fix weird leaks with flow observation
 */
class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val state = MutableLiveData<ViewState<List<ArticleSnapshot>>>()

    val articles: LiveData<ViewState<List<ArticleSnapshot>>>
        get() = state

    init {
        loadLatest()
    }

    private fun loadLatest() {
        launch {
            state.loaded(repository.observeArticles().first())
        }
    }
}