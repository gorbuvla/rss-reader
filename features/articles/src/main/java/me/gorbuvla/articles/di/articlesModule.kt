package me.gorbuvla.articles.di

import me.gorbuvla.articles.flow.detail.ArticleDetailViewModel
import me.gorbuvla.articles.flow.list.ArticleListViewModel
import me.gorbuvla.articles.model.ArticleRepository
import me.gorbuvla.articles.model.ArticleRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articlesModule = module {

    viewModel {
        ArticleListViewModel(repository = get())
    }

    viewModel { (id: Int) ->
        ArticleDetailViewModel(id = id, repository = get())
    }

    single<ArticleRepository> {
        ArticleRepositoryImpl(
            rss = get(),
            db = get()
        )
    }
}