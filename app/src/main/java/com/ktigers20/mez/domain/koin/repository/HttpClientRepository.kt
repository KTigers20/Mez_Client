package com.ktigers20.mez.domain.koin.repository

import okhttp3.OkHttpClient

interface HttpClientRepository {
    fun getRefreshOkHttp(): OkHttpClient

    fun getAccessOkHttp(): OkHttpClient
}
