package com.joesemper.redditpopularposts

import android.app.Application
import com.joesemper.redditpopularposts.di.appModule
import com.joesemper.redditpopularposts.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RedditApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RedditApp)
            modules(
                listOf(
                    appModule,
                    mainModule
                )
            )
        }
    }
}