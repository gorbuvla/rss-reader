package me.gorbuvla.articles.flow.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.first
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.core.model.ArticleRepository
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.launch
import me.gorbuvla.ui.util.loaded

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