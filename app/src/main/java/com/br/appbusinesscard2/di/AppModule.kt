package com.br.appbusinesscard2.di

import com.br.appbusinesscard2.data.BusinessCardDatabase
import com.br.appbusinesscard2.data.BusinessCardRepository
import com.br.appbusinesscard2.ui.addcard.AddCardViewModel
import com.br.appbusinesscard2.ui.home.HomeViewModel
import com.br.appbusinesscard2.usecase.ApplySearchFilterUseCase
import com.br.appbusinesscard2.usecase.ReadFromDatabaseUseCase
import com.br.appbusinesscard2.usecase.RemoveFromDatabaseUseCase
import com.br.appbusinesscard2.usecase.SaveToDatabaseUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { AddCardViewModel(get()) }
}

val repositoryModule = module {
    single { BusinessCardRepository(get()) }
}

val daoModule = module {
    single { BusinessCardDatabase.getInstance(androidContext()).businessCardDao }
}

val useCaseModule = module {
    factory { SaveToDatabaseUseCase(get()) }
    factory { ApplySearchFilterUseCase() }
    factory { RemoveFromDatabaseUseCase(get()) }
    factory { ReadFromDatabaseUseCase(get()) }
}