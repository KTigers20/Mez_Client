package com.ktigers20.mez.feature.phonebook

import android.annotation.SuppressLint
import android.util.Log
import com.ktigers20.mez.data.request.PersonInfoByJobRequest
import com.ktigers20.mez.data.request.PersonInfoByNameRequest
import com.ktigers20.mez.domain.api.usecase.GetPhoneBookInfoUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import retrofit2.HttpException
import timber.log.Timber

class PhonebookPresenter(
    override val view: PhonebookContract.View
    , private val getPhoneBookInfoUseCase: GetPhoneBookInfoUseCase
) : PhonebookContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getPhoneBookInfoByName(userName: String, pageNum: Long) {
        getPhoneBookInfoUseCase.getPhoneBookInfoByName(
            PersonInfoByNameRequest(userName, pageNum)
        ).subscribe({ phoneBookList ->
            phoneBookList.body()?.let {
                view.setPhoneBookList(it.contents)
                view.setPhoneBookPageIsEnd(it.pageable.totalPage <= pageNum)
                Log.e("PHONEBOOK_API", it.toString())
            } ?: run {

            }
        }, {
            handleError(it)
            Timber.e(it.localizedMessage)
        }).addTo(compositeDisposable)
    }

    @SuppressLint("CheckResult")
    override fun getPhoneBookInfoByJob(userJob: String, pageNum: Long) {
        getPhoneBookInfoUseCase.getPhoneBookInfoByJob(
            PersonInfoByJobRequest(userJob, pageNum)
        ).subscribe({ phoneBookList ->
            phoneBookList.body()?.let {
                view.setPhoneBookList(it.contents)
                view.setPhoneBookPageIsEnd(it.pageable.totalPage <= pageNum)
            } ?: run {

            }
        }, {
            handleError(it)
            Timber.e(it.localizedMessage)
        }).addTo(compositeDisposable)
    }

    private fun handleError(throwable: Throwable) {
        if (throwable is HttpException) {
            val statusCode = throwable.code()
            // handle different HTTP error codes here (4xx)
            if (statusCode == 400) {

            }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
