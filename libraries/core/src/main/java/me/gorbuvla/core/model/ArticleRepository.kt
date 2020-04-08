package me.gorbuvla.core.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.core.rss.RssInteractor
import kotlin.coroutines.CoroutineContext

/**
 * Repository to manage [Article].
 */
interface ArticleRepository {

    suspend fun fetchArticles()

    fun articles(): Flow<List<ArticleSnapshot>>

    fun article(id: Int): Flow<Article>
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

    init {
        observeChanges()
    }

    private fun observeChanges() {
        db.feeds()
            .map { rss.fetch(it.map { it.url }) }
            .onEach { db.store(it) }
            .launchIn(scope)
    }

    override suspend fun fetchArticles() {
        val feedUrls = db.feeds().first().map { it.url }
        db.store(rss.fetch(feedUrls))
    }

    override fun articles(): Flow<List<ArticleSnapshot>> {
        return db.articles().map { list ->
            list.map { it.toSnapshot() }
        }
    }

    override fun article(id: Int): Flow<Article> {
        return db.article(id)
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