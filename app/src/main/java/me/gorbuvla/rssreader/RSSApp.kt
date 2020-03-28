package me.gorbuvla.rssreader

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import me.gorbuvla.rssreader.di.apiModule
import me.gorbuvla.rssreader.di.repositoryModule
import me.gorbuvla.rssreader.di.viewModelModule
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
            modules(viewModelModule, repositoryModule, apiModule)
        }
    }
}