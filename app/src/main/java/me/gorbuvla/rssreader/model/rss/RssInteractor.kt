package me.gorbuvla.rssreader.model.rss

import android.text.Html
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndContent
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.impl.HttpURLFeedFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.gorbuvla.rssreader.model.domain.FeedDetail
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import timber.log.Timber
import java.net.URL


interface RssInteractor {

    suspend fun fetch(): List<FeedDetail>
}

class RssInteractorImpl: RssInteractor {

    private val feeds = listOf(
        URL("http://android-developers.blogspot.com/atom.xml"),
        URL("https://swift.org/atom.xml?format=xml")
    )

    override suspend fun fetch(): List<FeedDetail> {

        return withContext(Dispatchers.IO) {
            val fetcher = HttpURLFeedFetcher()

            val result = feeds.flatMap { url ->
                val entries = fetcher.retrieveFeed(url).entries
                
                Timber.i("RESULT: ${entries.first()}")

                entries.map { (it as SyndEntry).toFeed() }
            }

            result
        }
    }
}

private fun SyndEntry.toFeed(): FeedDetail {
    return FeedDetail(
        id = 1,
        title = title,
        content = (contents.first() as SyndContent).value,
        createdAt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(updatedDate.time), ZoneId.systemDefault())
    )
}