package me.gorbuvla.rssreader.di

import me.gorbuvla.rssreader.flows.feeddetail.FeedDetailViewModel
import me.gorbuvla.rssreader.flows.feedlist.FeedListViewModel
import me.gorbuvla.rssreader.model.FeedRepository
import me.gorbuvla.rssreader.model.FeedRepositoryImpl
import me.gorbuvla.rssreader.model.rss.RssInteractor
import me.gorbuvla.rssreader.model.rss.RssInteractorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Definition of DI modules
 */

val viewModelModule = module {

    viewModel {
        FeedListViewModel(repository = get())
    }

    viewModel { (id: Int) ->
        FeedDetailViewModel(
            id = id,
            repository = get()
        )
    }
}

val repositoryModule = module {

    single<FeedRepository> {
        FeedRepositoryImpl(rss = get())
    }
}

val apiModule = module {

    single<RssInteractor> {
        RssInteractorImpl()
    }
}