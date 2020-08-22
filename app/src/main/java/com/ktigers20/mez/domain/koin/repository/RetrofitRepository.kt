package com.ktigers20.mez.domain.koin.repository

import retrofit2.Retrofit

interface RefreshRetrofitRepository {
    fun getRefreshRetrofit(): Retrofit
}

interface AccessRetrofitRepository {
    fun getAccessRetrofit(): Retrofit
}
