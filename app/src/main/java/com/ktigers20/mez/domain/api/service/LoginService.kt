package com.ktigers20.mez.domain.api.service

import com.google.gson.JsonObject
import com.ktigers20.mez.data.request.LoginRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("loginEZ")
    fun getAccessToken(@Body loginRequest: LoginRequest): Single<JsonObject>
}
