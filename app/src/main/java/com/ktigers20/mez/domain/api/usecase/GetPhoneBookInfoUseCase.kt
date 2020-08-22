package com.ktigers20.mez.domain.api.usecase

import com.ktigers20.mez.data.request.PersonInfoByJobRequest
import com.ktigers20.mez.data.request.PersonInfoByNameRequest
import com.ktigers20.mez.data.response.PhoneBookResponse
import com.ktigers20.mez.domain.api.service.PhoneBookService
import com.ktigers20.mez.domain.koin.repository.AccessRetrofitRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers
import retrofit2.Response

//Liskov Substitution Principle
class GetPhoneBookInfoUseCase(accessRetrofitRepository: AccessRetrofitRepository) {
    private val phoneBookService =
        accessRetrofitRepository
            .getAccessRetrofit()
            .create(PhoneBookService::class.java)

    fun getPhoneBookInfoByName(personInfoByNameRequest: PersonInfoByNameRequest): Single<Response<List<PhoneBookResponse>>> =
        phoneBookService
            .getPersonInfoByName(personInfoByNameRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getPhoneBookInfoByJob(personInfoByJobRequest: PersonInfoByJobRequest): Single<Response<List<PhoneBookResponse>>> =
        phoneBookService
            .getPersonInfoByJob(personInfoByJobRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
