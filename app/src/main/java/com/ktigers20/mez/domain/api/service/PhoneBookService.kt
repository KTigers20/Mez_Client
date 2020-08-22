package com.ktigers20.mez.domain.api.service

import com.ktigers20.mez.data.request.PersonInfoByJobRequest
import com.ktigers20.mez.data.request.PersonInfoByNameRequest
import com.ktigers20.mez.data.response.PhoneBookResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PhoneBookService {
    @POST("getPersonInfo_byName")
    fun getPersonInfoByName(@Body phoneBookInfoByNameRequest: PersonInfoByNameRequest): Single<Response<PhoneBookResponse>>

    @POST("getPersonInfo_byJob")
    fun getPersonInfoByJob(@Body phoneBookInfoByJobRequest: PersonInfoByJobRequest): Single<Response<PhoneBookResponse>>

}
