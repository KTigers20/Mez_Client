package com.ktigers20.mez.domain.koin.repositoryImpl

import android.content.Context
import android.content.SharedPreferences
import com.ktigers20.mez.BuildConfig
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.koin.repository.HttpClientRepository
import com.ktigers20.mez.domain.sharedpref.SharedPreferenceHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class HttpClientRepositoryImpl(private val context: Context): HttpClientRepository {
    override fun getRefreshOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val refreshToken = SharedPreferenceHelper.getInstance(context).getPrefsStringValue(Consts.PREF_REFRESH_TOKEN)
            val request = chain.request()
            if(refreshToken.isNullOrEmpty()) {
                request.newBuilder()
                    .method(request.method(), request.body())
                    .addHeader("refreshToken", refreshToken)
                    .build()
            }
            chain.proceed(request)
        }

        //log
        if(BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        //timeout
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)

        return httpClient.build()
    }

    override fun getAccessOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val accessToken = SharedPreferenceHelper.getInstance(context).getPrefsStringValue(Consts.PREF_ACCESS_TOKEN)
            val request = chain.request()
            if(accessToken.isNullOrEmpty()) {
                request.newBuilder()
                    .method(request.method(), request.body())
                    .addHeader("accessToken", accessToken)
                    .build()
            }
            chain.proceed(request)
        }

        //log
        if(BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        //timeout
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)

        return httpClient.build()
    }

}