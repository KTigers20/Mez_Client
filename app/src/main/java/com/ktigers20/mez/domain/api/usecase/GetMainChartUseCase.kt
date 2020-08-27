package com.ktigers20.mez.domain.api.usecase

import com.ktigers20.mez.data.request.GetMyChartRequest
import com.ktigers20.mez.data.request.PersonInfoByJobRequest
import com.ktigers20.mez.data.response.GetAllChartResponse
import com.ktigers20.mez.data.response.GetMyChartResponse
import com.ktigers20.mez.domain.api.service.MainService
import com.ktigers20.mez.domain.koin.repository.AccessRetrofitRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class GetMainChartUseCase(accessRetrofitRepository: AccessRetrofitRepository) {
    private val mainService =
        accessRetrofitRepository
            .getAccessRetrofit()
            .create(MainService::class.java)

    fun getMyMainChartData(getMyChartRequest: GetMyChartRequest): Single<Response<List<GetMyChartResponse>>> =
        mainService
            .getMyMainChartData(getMyChartRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getAllMainChartData(personInfoByJobRequest: PersonInfoByJobRequest): Single<Response<List<GetAllChartResponse>>> =
        mainService
            .getAllMainChartData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
