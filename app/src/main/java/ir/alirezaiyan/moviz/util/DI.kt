package ir.alirezaiyan.moviz.util

import ir.alirezaiyan.moviz.BuildConfig
import ir.alirezaiyan.moviz.data.utils.NetworkHandler
import ir.alirezaiyan.moviz.data.utils.provideRetrofit
import ir.alirezaiyan.moviz.domain.feature.search.SearchRepository
import ir.alirezaiyan.moviz.feature.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val module: Module = module {

    viewModel { SearchViewModel(get()) }

    single { SearchRepository.SearchRepositoryImpl(get(), get()) } bind SearchRepository::class

    single { NetworkHandler(androidContext()) }
    single { provideRetrofit(BuildConfig.DEBUG) }
}