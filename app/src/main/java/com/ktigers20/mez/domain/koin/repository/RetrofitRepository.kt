package com.ktigers20.mez.domain.koin.repository

import retrofit2.Retrofit

interface RetrofitRepository {
    fun getRefreshRetrofit(): Retrofit

    fun getAccessRetrofit(): Retrofit
}