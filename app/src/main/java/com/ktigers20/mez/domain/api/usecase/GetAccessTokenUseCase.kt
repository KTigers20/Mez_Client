package com.ktigers20.mez.domain.api.usecase

import android.annotation.SuppressLint
import com.google.gson.JsonObject
import com.ktigers20.mez.data.request.LoginRequest
import com.ktigers20.mez.domain.api.service.LoginService
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.koin.repository.AccessRetrofitRepository
import com.ktigers20.mez.domain.koin.repository.SharedPrefRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.util.*

class GetAccessTokenUseCase(accessRetrofitRepository: AccessRetrofitRepository, var sharedPrefRepository: SharedPrefRepository) {
    private val loginService =
        accessRetrofitRepository.getAccessRetrofit().create(LoginService::class.java)

    @SuppressLint("CheckResult")
    fun getAccessToken(loginRequest: LoginRequest): Single<JsonObject> {
        sharedPrefRepository.writePrefs(Consts.TOKEN_TIME_KEY, Date().time)
        return loginService.getAccessToken(loginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
