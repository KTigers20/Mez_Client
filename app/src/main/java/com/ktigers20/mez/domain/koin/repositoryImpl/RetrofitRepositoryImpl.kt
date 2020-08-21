package com.ktigers20.mez.domain.koin.repositoryImpl

import com.ktigers20.mez.BuildConfig
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.koin.repository.HttpClientRepository
import com.ktigers20.mez.domain.koin.repository.RetrofitRepository
import com.ktigers20.mez.domain.utils.add
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepositoryImpl(private val okHttpRepo: HttpClientRepository): RetrofitRepository {
    private val versionInfo = "v1/"

    override fun getRefreshRetrofit(): Retrofit {
        val client = okHttpRepo.getRefreshOkHttp()
        val baseUrl: String = Consts.BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl add versionInfo)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    override fun getAccessRetrofit(): Retrofit {
        val client = okHttpRepo.getAccessOkHttp()
        val baseUrl: String = Consts.BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl add versionInfo)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}