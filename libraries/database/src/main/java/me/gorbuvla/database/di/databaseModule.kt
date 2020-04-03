package me.gorbuvla.database.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import me.gorbuvla.core.database.DatabaseInteractor
import me.gorbuvla.database.DbInteractorImpl
import me.gorbuvla.database.RssDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidContext(), RssDatabase::class.java, "rss.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    db.execSQL("INSERT INTO feed (id, name, url) VALUES(1, 'Android Developers Blog', 'http://android-developers.blogspot.com/atom.xml')")
                    db.execSQL("INSERT INTO feed (id, name, url) VALUES(2, 'Swift News', 'https://swift.org/atom.xml?format=xml')")
                }
            })
            .build()
    }

    single { get<RssDatabase>().articleDao() }

    single { get<RssDatabase>().feedDao() }

    single<DatabaseInteractor> {
        DbInteractorImpl(articleDao = get(), feedDao = get())
    }
}