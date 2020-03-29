package me.gorbuvla.articles.flow.list

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import me.gorbuvla.articles.model.ArticleRepository
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.into
import me.gorbuvla.ui.util.*
import timber.log.Timber

/**
 * ViewModel for screen with feed items.
 */
class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val loadingState = MutableLiveData<ViewState<Unit>>()

    val loading: LiveData<Boolean>
        get() = loadingState.map { it is ViewState.Loading }

    val state = MediatorLiveData<List<Article>>().apply {
        value = emptyList()
        addSource(repository.observeArticles().asLiveData()) {
            value = it
        }
    }

    val articles: LiveData<List<Article>>
        get() = state

    init {
        loadLatest()
    }

    private fun loadLatest() {
        loadingState.loading()
        launch { repository.fetchArticles() }
    }
}