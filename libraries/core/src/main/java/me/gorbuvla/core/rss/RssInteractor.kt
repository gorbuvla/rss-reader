package me.gorbuvla.core.rss

import me.gorbuvla.core.domain.Article
import java.net.URL

/**
 * RSS contract
 */
interface RssInteractor {

    suspend fun fetch(urls: List<URL>): List<Article>
}