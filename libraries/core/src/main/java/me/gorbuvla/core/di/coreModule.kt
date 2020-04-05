package me.gorbuvla.core.di

import me.gorbuvla.core.model.ArticleRepository
import me.gorbuvla.core.model.ArticleRepositoryImpl
import org.koin.dsl.module

val coreModule = module {

    single<ArticleRepository> {
        ArticleRepositoryImpl(
            rss = get(),
            db = get()
        )
    }
}