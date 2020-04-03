package me.gorbuvla.feeds.model

import kotlinx.coroutines.flow.Flow
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.core.domain.Feed

/**
 * Repository to manage [Feed].
 */
interface FeedRepository {

    fun feeds(): Flow<List<Feed>>
}

class FeedRepositoryImpl(private val db: DatabaseInteractor): FeedRepository {

    override fun feeds(): Flow<List<Feed>> {
        return db.feeds()
    }
}