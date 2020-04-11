package me.gorbuvla.articles.flow.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.core.model.ArticleRepository
import me.gorbuvla.ui.util.Result
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.error
import me.gorbuvla.ui.util.launch
import me.gorbuvla.ui.util.loaded
import me.gorbuvla.ui.util.loading
import me.gorbuvla.ui.util.wrapResult

/**
 * ViewModel for screen with feed items.
 */
class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val loadingState = MutableLiveData<ViewState<Unit>>()

    val loading: LiveData<ViewState<Unit>>
        get() = repository.fetchState.asLiveData()

    val articles: LiveData<ViewState<List<ArticleSnapshot>>>
        get() = repository.articles().asLiveData().map { if (it.isNotEmpty()) ViewState.Loaded(it) else ViewState.Empty }

    fun loadLatest() {
        loadingState.loading()
        launch {
            when (val result = wrapResult { repository.fetchArticles() }) {
                is Result.success -> loadingState.loaded()
                is Result.failure -> loadingState.error(result.error)
            }
        }
    }
}