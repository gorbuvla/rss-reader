package me.gorbuvla.rssreader.model

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.gorbuvla.rssreader.model.domain.FeedDetail
import me.gorbuvla.rssreader.model.domain.FeedItem
import me.gorbuvla.rssreader.model.rss.RssInteractor

/**
 * Repository to retrieve & manage feed.
 */
interface FeedRepository {

    suspend fun fetchFeed(): List<FeedItem>

    suspend fun fetchDetail(id: Int): FeedDetail

    suspend fun observeFeedList(): Flow<List<FeedItem>>

    suspend fun observeFeedDetail(id: Int): Flow<FeedDetail>
}

internal class FeedRepositoryImpl(private val rss: RssInteractor): FeedRepository {

    private val blogs: List<FeedDetail>
        get() = blogsHavryluk + blogsCermak + blogsSkoumal + blogsBildik

    override suspend fun fetchFeed(): List<FeedItem> {
        return rss.fetch().map { it.toFeedItem() }
    }

    override suspend fun observeFeedList(): Flow<List<FeedItem>> {
        return flow { emit(rss.fetch().map { it.toFeedItem() }) }
    }

    override suspend fun observeFeedDetail(id: Int): Flow<FeedDetail> {
        return flow { emit(rss.fetch().first { it.id == id }) }
    }

    override suspend fun fetchDetail(id: Int): FeedDetail {
        return blogs.firstOrNull { it.id == id } ?: throw IllegalStateException("Could not find blog for specified id: $id")
    }

    private fun FeedDetail.toFeedItem(): FeedItem {
        return FeedItem(id, title, content.take(400), createdAt)
    }

}