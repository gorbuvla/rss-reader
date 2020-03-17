package me.gorbuvla.rssreader

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import me.gorbuvla.rssreader.di.repositoryModule
import me.gorbuvla.rssreader.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RSSApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@RSSApp)
            modules(viewModelModule, repositoryModule)
        }
    }
}