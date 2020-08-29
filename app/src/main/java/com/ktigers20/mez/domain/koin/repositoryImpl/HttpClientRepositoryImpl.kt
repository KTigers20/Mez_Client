package com.ktigers20.mez.domain.koin.repositoryImpl


import com.ktigers20.mez.BuildConfig
import com.ktigers20.mez.data.request.LoginRequest
import com.ktigers20.mez.domain.api.usecase.GetAccessTokenUseCase
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.koin.repository.AccessClientRepository
import com.ktigers20.mez.domain.koin.repository.RefreshClientRepository
import com.ktigers20.mez.domain.koin.repository.SharedPrefRepository
import com.ktigers20.mez.domain.utils.ConverterUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
import java.util.concurrent.TimeUnit

class RefreshClientRepositoryImpl(private val sharedPrefRepository: SharedPrefRepository) :
    RefreshClientRepository {
    override fun getRefreshOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            val refreshToken =
                ConverterUtil._Decode(sharedPrefRepository.getPrefsStringValue(Consts.REFRESH_TOKEN))
                    ?: ""
            var request = chain.request()
            if (refreshToken.isNotEmpty()) {
                request = request.newBuilder()
                    .method(request.method(), request.body())
                    .addHeader("Authorization", "Bearer $refreshToken")
                    .build()
            }
            chain.proceed(request)
        }

        //log
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        // timeout
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)

        return httpClient.build()
    }
}

class AccessClientRepositoryImpl(
    private val sharedPrefRepository: SharedPrefRepository
) :
    AccessClientRepository {
    override fun getAccessOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            var accessToken =
                ConverterUtil._Decode(sharedPrefRepository.getPrefsStringValue(Consts.ACCESS_TOKEN))
                    ?: ""
            val refreshTime = sharedPrefRepository.getPrefsLongValue(Consts.TOKEN_TIME_KEY)
            val newTime = Date().time
//            if (newTime - refreshTime > 1200000) {
//                val response = getAccessTokenUseCase.getAccessToken(
//                    LoginRequest(
//                        UserInfo.userId,
//                        UserInfo.userId
//                    )
//                ).blockingGet()
//                accessToken = response.body()!!.token
//                sharedPrefRepository.writePrefs(
//                    Consts.ACCESS_TOKEN,
//                    ConverterUtil._Encode(accessToken)
//                )
//            }

            var request = chain.request()
            if (accessToken.isNotEmpty()) {
                request = request.newBuilder()
                    .method(request.method(), request.body())
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()
            }
            return@addInterceptor chain.proceed(request)
        }

        //log
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        // timeout
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.connectTimeout(30, TimeUnit.SECONDS)

        return httpClient.build()
    }
}
