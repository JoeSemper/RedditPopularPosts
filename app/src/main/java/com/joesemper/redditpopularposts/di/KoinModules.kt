package com.joesemper.redditpopularposts.di

import androidx.room.Room
import com.joesemper.redditpopularposts.data.datasource.local.PostsDatabase
import com.joesemper.redditpopularposts.data.datasource.remote.PostRepositoryRetrofitImpl
import com.joesemper.redditpopularposts.data.repository.PostsRepository
import com.joesemper.redditpopularposts.ui.main.MainFragment
import com.joesemper.redditpopularposts.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<PostsRepository> { PostRepositoryRetrofitImpl(get()) }
    single<PostsDatabase> {
        Room.databaseBuilder(
            androidContext(),
            PostsDatabase::class.java, "database"
        ).build()
    }
}

val mainModule = module {
    scope(named<MainFragment>()) {
        viewModel { MainViewModel(get()) }
    }

}