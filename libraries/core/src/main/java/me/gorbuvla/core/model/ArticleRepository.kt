package me.gorbuvla.core.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.core.rss.RssInteractor
import me.gorbuvla.ui.util.ViewState
import kotlin.coroutines.CoroutineContext

/**
 * Repository to manage [Article].
 */
interface ArticleRepository {

    val fetchState: Flow<ViewState<Unit>>

    suspend fun fetchArticles()

    fun articles(): Flow<List<ArticleSnapshot>>

    fun article(id: String): Flow<Article>
}

internal class ArticleRepositoryImpl(
    private val rss: RssInteractor,
    private val db: DatabaseInteractor
): ArticleRepository {

    private val scope = object : CoroutineScope {

        private val job = Job()

        override val coroutineContext: CoroutineContext
            get() = job + Dispatchers.IO
    }

    private val fetchStateChannel = ConflatedBroadcastChannel<ViewState<Unit>>()

    override val fetchState: Flow<ViewState<Unit>>
        get() = fetchStateChannel.asFlow()

    init {
        observeChanges()
    }

    private fun observeChanges() {
        db.feeds().distinctUntilChanged()
            .onEach { fetchArticles() }
            .launchIn(scope)
    }

    override suspend fun fetchArticles() {
        fetchStateChannel.offer(ViewState.Loading)

        try {
            val feedUrls = db.feeds().first().map { it.url }
            db.store(rss.fetch(feedUrls))
            fetchStateChannel.offer(ViewState.Loaded(Unit))
        } catch (e: Exception) {
            fetchStateChannel.offer(ViewState.Error(e))
        }
    }

    override fun articles(): Flow<List<ArticleSnapshot>> {
        return db.articles()
            .distinctUntilChanged()
            .map { list -> list.map { it.toSnapshot() } }
    }

    override fun article(id: String): Flow<Article> {
        return db.article(id)
            .distinctUntilChanged()
    }

    private fun Article.toSnapshot(): ArticleSnapshot {
        return ArticleSnapshot(
            id = id,
            title = title,
            preview = content.take(400),
            createdAt = createdAt
        )
    }
}