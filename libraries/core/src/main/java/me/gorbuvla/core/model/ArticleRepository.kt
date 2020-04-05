package me.gorbuvla.core.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.domain.ArticleSnapshot
import me.gorbuvla.core.rss.RssInteractor

/**
 * Repository to manage [Article].
 */
interface ArticleRepository {

    suspend fun fetchArticles()

    fun observeArticles(): Flow<List<ArticleSnapshot>>

    fun observeArticle(id: Int): Flow<Article>
}

internal class ArticleRepositoryImpl(
    private val rss: RssInteractor,
    private val db: DatabaseInteractor
): ArticleRepository {

    override suspend fun fetchArticles() {
        val feedUrls = db.feeds().first().map { it.url }
        db.store(rss.fetch(feedUrls))
    }

    override fun observeArticles(): Flow<List<ArticleSnapshot>> {
        return db.articles().map { list ->
            list.map { it.toSnapshot() }
        }
    }

    override fun observeArticle(id: Int): Flow<Article> {
        return db.article(id)
    }

    private fun Article.toSnapshot(): ArticleSnapshot {
        return ArticleSnapshot(
            id = id,
            title = title,
            preview = content.take(400)
        )
    }
}