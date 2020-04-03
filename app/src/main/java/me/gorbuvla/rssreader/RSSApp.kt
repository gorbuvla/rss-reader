package me.gorbuvla.rssreader

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import me.gorbuvla.articles.di.articlesModule
import me.gorbuvla.database.di.databaseModule
import me.gorbuvla.rss.rssModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RSSApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@RSSApp)
            modules(articlesModule, rssModule, databaseModule)
        }
    }
}