package com.ktigers20.mez.domain.api.service

import com.ktigers20.mez.data.request.GetMyChartRequest
import com.ktigers20.mez.data.request.PersonInfoByNameRequest
import com.ktigers20.mez.data.response.GetAllChartResponse
import com.ktigers20.mez.data.response.GetMyChartResponse
import com.ktigers20.mez.data.response.PhoneBookResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainService {
    @POST("getMainChartData_My")
    fun getMyMainChartData(@Body getMyChartRequest: GetMyChartRequest): Single<Response<List<GetMyChartResponse>>>

    @POST("getMainChartData_All")
    fun getAllMainChartData(): Single<Response<List<GetAllChartResponse>>>
}
