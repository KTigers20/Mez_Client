package com.ktigers20.mez.feature.search

import android.annotation.SuppressLint
import com.ktigers20.mez.data.request.SearchRequest
import com.ktigers20.mez.domain.api.usecase.GetBatchInfoUseCase
import com.ktigers20.mez.singleton.SearchFilter
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import timber.log.Timber

class SearchPresenter(
    override val view: SearchContract.View
    , private val getBatchInfoUseCase: GetBatchInfoUseCase
) : SearchContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getBatchInfo(searchFilter: SearchFilter, pageNum: Long) {
        getBatchInfoUseCase.getBatchInfoByFilter(
            SearchRequest(
                pageNum,
                searchFilter.deptName,
                searchFilter.userName,
                searchFilter.jobName,
                searchFilter.status,
                searchFilter.application,
                searchFilter.groupName,
                searchFilter.startDate,
                searchFilter.endDate
            )
        ).subscribe({ batchList ->
            batchList.body()?.let {
                view.setBatchInfoList(it.contents)
                view.setSearchPageIsEnd(it.pageable.totalPage - 1 <= pageNum)
            } ?: run {

            }
        }, {
            handleError(it)
            Timber.e(it.localizedMessage)
        })
    }

    private fun handleError(throwable: Throwable) {
        if (throwable is HttpException) {
            val statusCode = throwable.code()
            //handle different HTTP error codes here(4xx)
            if (statusCode == 400) {

            }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
