package me.gorbuvla.rssreader.model

import kotlinx.coroutines.delay
import me.gorbuvla.rssreader.model.domain.FeedDetail
import me.gorbuvla.rssreader.model.domain.FeedItem

/**
 * Repository to retrieve & manage feed.
 */
interface FeedRepository {

    suspend fun fetchFeed(): List<FeedItem>

    suspend fun fetchDetail(id: Int): FeedDetail
}

internal class FeedRepositoryImpl: FeedRepository {

    private val blogs: List<FeedDetail>
        get() = blogsHavryluk + blogsCermak + blogsSkoumal + blogsBildik

    override suspend fun fetchFeed(): List<FeedItem> {
        delay(1000)
        return blogs.map { it.toFeedItem() }
    }

    override suspend fun fetchDetail(id: Int): FeedDetail {
        return blogs.firstOrNull { it.id == id } ?: throw IllegalStateException("Could not find blog for specified id: $id")
    }

    private fun FeedDetail.toFeedItem(): FeedItem {
        return FeedItem(id, title, content.take(400), createdAt)
    }

}