package me.gorbuvla.core.database

import kotlinx.coroutines.flow.Flow
import me.gorbuvla.core.domain.Article

/**
 * Database contract
 */
interface DatabaseInteractor {

    suspend fun store(data: List<Article>)

    fun observeArticles(): Flow<List<Article>>

    fun observeArticle(id: Long): Flow<Article>
}