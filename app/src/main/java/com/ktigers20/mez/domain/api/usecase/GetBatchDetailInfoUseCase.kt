package com.ktigers20.mez.domain.api.usecase

import com.ktigers20.mez.data.request.SearchDetailRequest
import com.ktigers20.mez.data.response.SearchDetailResponse
import com.ktigers20.mez.domain.api.service.SearchService
import com.ktigers20.mez.domain.koin.repository.AccessRetrofitRepository
import io.reactivex.Single

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

//Liskov Substitution Principle
class GetBatchDetailInfoUseCase(accessRetrofitRepository: AccessRetrofitRepository) {
    private val searchService =
        accessRetrofitRepository.getAccessRetrofit().create(SearchService::class.java)

    fun getBatchDetailInfoByOID(searchDetailRequest: SearchDetailRequest): Single<List<SearchDetailResponse>> =
        searchService.getBatchDetailInfoByOID(searchDetailRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
