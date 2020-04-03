package me.gorbuvla.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.domain.Feed
import me.gorbuvla.database.entities.ArticleDao
import me.gorbuvla.database.entities.DbArticle
import me.gorbuvla.database.entities.DbFeed
import me.gorbuvla.database.entities.FeedDao

/**
 * Shared Access point for all database operations.
 */
typealias DbInteractor = DatabaseInteractor

class DbInteractorImpl(
    private val articleDao: ArticleDao,
    private val feedDao: FeedDao
): DbInteractor {

    override suspend fun store(data: List<Article>) {
        articleDao.clear()
        articleDao.insert(*data.map { it.toDbArticle() }.toTypedArray())
    }

    override fun articles(): Flow<List<Article>> {
        return articleDao.articles()
            .distinctUntilChanged()
            .map { list -> list.map { it.toArticle() } }
    }

    override fun article(id: Long): Flow<Article> {
        return articleDao.article(id)
            .distinctUntilChanged()
            .map { it.first().toArticle() }
    }

    override suspend fun store(feed: Feed) {
        feedDao.insert(feed.toDbFeed())
    }

    override fun feeds(): Flow<List<Feed>> {
        return feedDao.feeds()
            .map { list -> list.map { it.toFeed() } }
    }

    override suspend fun remove(feed: Feed) {
        feedDao.delete(feed.id)
    }

    private fun Article.toDbArticle(): DbArticle {
        return DbArticle(
            id = 0,
            title = title,
            content = content
        )
    }

    private fun Feed.toDbFeed(): DbFeed {
        return DbFeed(
            id = 0,
            name = name,
            url = url.toString()
        )
    }
}