package me.gorbuvla.feeds.model

import kotlinx.coroutines.flow.Flow
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Feed
import java.net.URL

/**
 * Repository to manage [Feed].
 */
interface FeedRepository {

    suspend fun add(name: String, url: URL)

    suspend fun remove(feed: Feed)

    fun feeds(): Flow<List<Feed>>
}

class FeedRepositoryImpl(private val db: DatabaseInteractor): FeedRepository {

    override suspend fun add(name: String, url: URL) = db.store(Feed(id = url.toString(), name = name, url = url))

    override suspend fun remove(feed: Feed) = db.remove(feed)

    override fun feeds(): Flow<List<Feed>> = db.feeds()
}