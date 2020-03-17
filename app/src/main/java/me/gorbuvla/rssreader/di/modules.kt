package me.gorbuvla.rssreader.di

import me.gorbuvla.rssreader.flows.feeddetail.FeedDetailViewModel
import me.gorbuvla.rssreader.flows.feedlist.FeedListViewModel
import me.gorbuvla.rssreader.model.FeedRepository
import me.gorbuvla.rssreader.model.FeedRepositoryImpl
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
        FeedRepositoryImpl()
    }
}