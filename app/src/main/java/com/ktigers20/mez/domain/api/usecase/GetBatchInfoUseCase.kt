package com.ktigers20.mez.domain.api.usecase

import com.ktigers20.mez.data.request.SearchRequest
import com.ktigers20.mez.data.response.SearchResponse
import com.ktigers20.mez.domain.api.service.SearchService
import com.ktigers20.mez.domain.koin.repository.AccessRetrofitRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

//Liskov Substitution Principle
class GetBatchInfoUseCase(accessRetrofitRepository: AccessRetrofitRepository) {
    private val searchService =
        accessRetrofitRepository
            .getAccessRetrofit()
            .create(SearchService::class.java)

    fun getBatchInfoByFilter(searchRequest: SearchRequest): Single<Response<SearchResponse>> =
        searchService
            .getBatchInfoByFilter(searchRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

