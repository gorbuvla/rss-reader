package me.gorbuvla.feeds.di

import me.gorbuvla.feeds.flows.list.FeedsViewModel
import me.gorbuvla.feeds.model.FeedRepository
import me.gorbuvla.feeds.model.FeedRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val feedsModule = module {

    viewModel {
        FeedsViewModel(repository = get())
    }

    single<FeedRepository> {
        FeedRepositoryImpl(db = get())
    }
}