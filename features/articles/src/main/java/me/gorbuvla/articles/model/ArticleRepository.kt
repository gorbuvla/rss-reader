package me.gorbuvla.articles.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.rss.RssInteractor
import timber.log.Timber
import java.net.URL

/**
 * TODO add class description
 */
interface ArticleRepository {

    suspend fun fetchArticles()

    fun observeArticles(): Flow<List<Article>>

    fun observeArticle(id: Long): Flow<Article>
}

internal class ArticleRepositoryImpl(
    private val rss: RssInteractor,
    private val db: DatabaseInteractor
): ArticleRepository {

    private val feeds = listOf(
        URL("http://android-developers.blogspot.com/atom.xml"),
        URL("https://swift.org/atom.xml?format=xml")
    )

    override suspend fun fetchArticles() {
        db.store(rss.fetch(feeds))
    }

    override fun observeArticles(): Flow<List<Article>> {
        return db.observeArticles()
    }

    override fun observeArticle(id: Long): Flow<Article> {
        Timber.i("ARTICLE ${id}")
        return db.observeArticle(id)
    }
}