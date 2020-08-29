package com.ktigers20.mez.domain.api.service

import com.ktigers20.mez.data.request.GetMyChartRequest
import com.ktigers20.mez.data.request.LoginRequest
import com.ktigers20.mez.data.response.GetMyChartResponse
import com.ktigers20.mez.data.response.LoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("loginEZ")
    fun getAccessToken(@Body loginRequest: LoginRequest): Single<Response<LoginResponse>>
}
