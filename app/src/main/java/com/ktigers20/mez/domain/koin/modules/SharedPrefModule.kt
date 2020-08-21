package com.ktigers20.mez.domain.koin.modules

import com.ktigers20.mez.domain.koin.repository.SharedPrefRepository
import com.ktigers20.mez.domain.koin.repositoryImpl.SharedPrefRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sharedPrefModule = module {
    single<SharedPrefRepository> { SharedPrefRepositoryImpl(androidApplication()) }
}
