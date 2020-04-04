package me.gorbuvla.articles.flow.list

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import me.gorbuvla.articles.model.ArticleRepository
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.into
import me.gorbuvla.ui.util.*
import timber.log.Timber
import kotlin.coroutines.coroutineContext

/**
 * ViewModel for screen with feed items.
 * TODO: fix weird leaks with flow observation
 */
class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val state = MutableLiveData<ViewState<List<Article>>>()

    val articles: LiveData<ViewState<List<Article>>>
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