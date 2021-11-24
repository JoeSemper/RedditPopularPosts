package com.joesemper.redditpopularposts.di

import com.joesemper.redditpopularposts.data.datasource.remote.PostRepositoryRetrofitImpl
import com.joesemper.redditpopularposts.data.repository.PostsRepository
import com.joesemper.redditpopularposts.ui.main.MainFragment
import com.joesemper.redditpopularposts.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<PostsRepository> { PostRepositoryRetrofitImpl() }
}

val mainModule = module {
    scope(named<MainFragment>()) {
        viewModel { MainViewModel(get()) }
    }

}