package me.gorbuvla.database.di

import androidx.room.Room
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.database.DbInteractorImpl
import me.gorbuvla.database.RssDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { Room.databaseBuilder(androidContext(), RssDatabase::class.java, "rss.dp").build() }

    single { get<RssDatabase>().feedDao() }

    single<DatabaseInteractor> {
        DbInteractorImpl(dao = get())
    }
}