package me.gorbuvla.articles.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.rss.RssInteractor

/**
 * Repository to manage [Article].
 */
interface ArticleRepository {

    suspend fun fetchArticles()

    fun observeArticles(): Flow<List<Article>>

    fun observeArticle(id: String): Flow<Article>
}

internal class ArticleRepositoryImpl(
    private val rss: RssInteractor,
    private val db: DatabaseInteractor
): ArticleRepository {

    override suspend fun fetchArticles() {
        val feedUrls = db.feeds().first().map { it.url }
        db.store(rss.fetch(feedUrls))
    }

    override fun observeArticles(): Flow<List<Article>> {
        return db.articles()
    }

    override fun observeArticle(id: String): Flow<Article> {
        return db.article(id)
    }
}