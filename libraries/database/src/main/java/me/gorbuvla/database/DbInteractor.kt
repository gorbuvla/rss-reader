package me.gorbuvla.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Article
import me.gorbuvla.database.entities.ArticleDao
import me.gorbuvla.database.entities.DbArticle
import timber.log.Timber

/**
 * Shared Access point for all database operations.
 */
typealias DbInteractor = DatabaseInteractor

class DbInteractorImpl(private val dao: ArticleDao): DbInteractor {

    override suspend fun store(data: List<Article>) {
        dao.clear()
        dao.insert(*data.map { it.toDbArticle() }.toTypedArray())
    }

    override fun observeArticles(): Flow<List<Article>> {
        return dao.articles().map { list ->
            list.map {
                Timber.i("ARTICLE ${it.id}")
                it.toArticle() }
        }
    }

    override fun observeArticle(id: Long): Flow<Article> {
        return dao.article(id).map {
            Timber.i("ARTICLE ID: ${it.firstOrNull()?.id} ${id}")
            it.first().toArticle()

        }
    }

    private fun Article.toDbArticle(): DbArticle {
        return DbArticle(
            id = 0,
            title = title,
            content = content
        )
    }
}