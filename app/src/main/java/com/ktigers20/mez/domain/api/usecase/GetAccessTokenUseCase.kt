package com.ktigers20.mez.domain.api.usecase

import com.ktigers20.mez.data.request.LoginRequest
import com.ktigers20.mez.data.response.LoginResponse
import com.ktigers20.mez.domain.api.service.LoginService
import com.ktigers20.mez.domain.koin.repository.AccessRetrofitRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class GetAccessTokenUseCase(accessRetrofitRepository: AccessRetrofitRepository) {
    private val loginService =
        accessRetrofitRepository.getAccessRetrofit().create(LoginService::class.java)

    fun getAccessToken(loginRequest: LoginRequest): Single<Response<LoginResponse>> =
        loginService.getAccessToken(loginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
