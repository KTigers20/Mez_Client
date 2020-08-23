package com.ktigers20.mez.domain.koin.modules

import com.ktigers20.mez.domain.koin.repository.AccessClientRepository
import com.ktigers20.mez.domain.koin.repository.AccessRetrofitRepository
import com.ktigers20.mez.domain.koin.repository.RefreshClientRepository
import com.ktigers20.mez.domain.koin.repository.RefreshRetrofitRepository
import com.ktigers20.mez.domain.koin.repositoryImpl.AccessClientRepositoryImpl
import com.ktigers20.mez.domain.koin.repositoryImpl.AccessRetrofitRepositoryImpl
import com.ktigers20.mez.domain.koin.repositoryImpl.RefreshClientRepositoryImpl
import com.ktigers20.mez.domain.koin.repositoryImpl.RefreshRetrofitRepositoryImpl
import org.koin.dsl.module

val networkModule = module {
    single<RefreshClientRepository> { RefreshClientRepositoryImpl(get())}

    single<AccessClientRepository> { AccessClientRepositoryImpl(get()) }

    single<RefreshRetrofitRepository> { RefreshRetrofitRepositoryImpl(get()) }

    single<AccessRetrofitRepository> { AccessRetrofitRepositoryImpl(get()) }
}
