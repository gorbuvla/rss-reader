package me.gorbuvla.core.database

import kotlinx.coroutines.flow.Flow
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.domain.Feed

/**
 * Database contract
 */
interface DatabaseInteractor {

    suspend fun store(data: List<Article>)

    fun articles(): Flow<List<Article>>

    fun article(id: Long): Flow<Article>

    suspend fun store(feed: Feed)

    fun feeds(): Flow<List<Feed>>

    suspend fun remove(feed: Feed)
}