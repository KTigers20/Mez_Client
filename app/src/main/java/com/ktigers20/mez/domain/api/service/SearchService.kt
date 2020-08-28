package com.ktigers20.mez.domain.api.service

import com.ktigers20.mez.data.request.SearchDetailRequest
import com.ktigers20.mez.data.request.SearchRequest
import com.ktigers20.mez.data.response.SearchDetailResponse
import com.ktigers20.mez.data.response.SearchResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SearchService {
    @POST("getHistroy_byFilter")
    fun getBatchInfoByFilter(@Body searchRequest: SearchRequest): Single<Response<SearchResponse>>

    @POST("getBatchDetail_byOID")
    fun getBatchDetailInfoByOID(@Body searchDetailRequest: SearchDetailRequest): Single<List<SearchDetailResponse>>
}
