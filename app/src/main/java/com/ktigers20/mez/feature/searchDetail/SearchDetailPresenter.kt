package com.ktigers20.mez.feature.searchDetail

import android.annotation.SuppressLint
import android.util.Log
import com.ktigers20.mez.data.entity.BatchDetailInfo
import com.ktigers20.mez.data.request.SearchDetailRequest
import com.ktigers20.mez.domain.api.usecase.GetBatchDetailInfoUseCase
import com.ktigers20.mez.domain.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import timber.log.Timber

class SearchDetailPresenter(
    override val view: SearchDetailContract.View,
    private val getBatchDetailInfoUseCase: GetBatchDetailInfoUseCase
) : SearchDetailContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getBatchDetailInfo(orderId: String) {
        getBatchDetailInfoUseCase.getBatchDetailInfoByOID(SearchDetailRequest(orderId))
            .subscribe({
                view.setBatchDetailInfo(
                    BatchDetailInfo(
                        it[0].jOB_NAME,
                        it[0].uSER1DEPT,
                        it[0].uSER1NAME,
                        it[0].uSER1HP,
                        it[0].uSER2DEPT,
                        it[0].uSER2NAME,
                        it[0].uSER2HP,
                        it[0].uSER3DEPT,
                        it[0].uSER3NAME,
                        it[0].uSER3HP,
                        it[0].uSER4DEPT,
                        it[0].uSER4NAME,
                        it[0].uSER4HP,
                        it[0].oRDER_TIME,
                        it[0].aPPLICATION,
                        it[0].gROUP_NAME,
                        it[0].dESCRIPTION,
                        it[0].nODE_ID,
                        it[0].cMD_LINE,
                        it[0].sTATUS,
                        it[0].eRROR_DESCRIPTION
                    )
                )
            }, {
                handleError(it)
                Timber.e(it.localizedMessage)
            }).addTo(compositeDisposable)
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