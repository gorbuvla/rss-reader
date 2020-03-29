package me.gorbuvla.rss

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndContent
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FeedFetcher
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.impl.HttpURLFeedFetcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.gorbuvla.core.domain.Article
import me.gorbuvla.core.rss.RssInteractor
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import timber.log.Timber
import java.net.URL

/**
 * TODO add class description
 */
class RssService(private val fetcher: FeedFetcher) : RssInteractor {

    override suspend fun fetch(urls: List<URL>): List<Article> {
        return withContext(Dispatchers.IO) {
            val result = urls.flatMap { url ->
                val entries = fetcher.retrieveFeed(url).entries

                Timber.i("RESULT: ${entries.first()}")

                entries.map { (it as SyndEntry).toArticle() }
            }

            result
        }
    }
}

private fun SyndEntry.toArticle(): Article {
    return Article(
        id = 0,
        title = title,
        content = (contents.first() as SyndContent).value,
        createdAt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(updatedDate.time), ZoneId.systemDefault())
    )
}