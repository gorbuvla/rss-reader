package me.gorbuvla.rss

import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.FeedFetcher
import com.google.code.rome.android.repackaged.com.sun.syndication.fetcher.impl.HttpURLFeedFetcher
import me.gorbuvla.core.rss.RssInteractor
import org.koin.dsl.module

val rssModule = module {

    single<RssInteractor> {
        RssService(fetcher = get())
    }

    single<FeedFetcher> {
        HttpURLFeedFetcher()
    }
}