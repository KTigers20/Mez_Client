package com.ktigers20.mez.domain.koin.repositoryImpl

import android.annotation.SuppressLint
import com.ktigers20.mez.BuildConfig
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.globalconst.UrlConst
import com.ktigers20.mez.domain.koin.repository.*
import com.ktigers20.mez.domain.utils.ConverterUtil
import com.ktigers20.mez.domain.utils.add
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RefreshRetrofitRepositoryImpl(private val okHttpRepo: RefreshClientRepository):
    RefreshRetrofitRepository {
    @SuppressLint("DefaultLocale")
    override fun getRefreshRetrofit(): Retrofit {
        val client = okHttpRepo.getRefreshOkHttp()
        val baseUrl: String = ConverterUtil._Decode(UrlConst.DEV_URL)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}

class AccessRetrofitRepositoryImpl(private val okHttpRepo: AccessClientRepository): AccessRetrofitRepository {
    @SuppressLint("DefaultLocale")
    override fun getAccessRetrofit(): Retrofit {
        val client = okHttpRepo.getAccessOkHttp()
        val baseUrl: String = ConverterUtil._Decode(UrlConst.DEV_URL)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}