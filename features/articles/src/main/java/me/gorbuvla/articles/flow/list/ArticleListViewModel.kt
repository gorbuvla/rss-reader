package me.gorbuvla.articles.flow.list

import androidx.lifecycle.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.core.model.ArticleRepository
import me.gorbuvla.ui.util.Result
import me.gorbuvla.ui.util.ViewState
import me.gorbuvla.ui.util.error
import me.gorbuvla.ui.util.launch
import me.gorbuvla.ui.util.loaded
import me.gorbuvla.ui.util.loading
import me.gorbuvla.ui.util.wrapResult
import kotlin.coroutines.coroutineContext

/**
 * ViewModel for screen with feed items.
 */
class ArticleListViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val articleState = MutableLiveData<ViewState<List<ArticleSnapshot>>>()

    val loading: LiveData<ViewState<Unit>>
        get() = repository.fetchState.asLiveData(viewModelScope.coroutineContext)

    val articles: LiveData<ViewState<List<ArticleSnapshot>>>
        get() = articleState

    init {
        repository.articles()
            .onEach { articleState.loaded(it) }
            .catch { articleState.error(it) }
            .launchIn(viewModelScope)
    }

    fun loadLatest() {
        launch { repository.fetchArticles() }
    }
}