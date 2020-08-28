package com.ktigers20.mez.feature.main

import android.annotation.SuppressLint
import com.ktigers20.mez.data.request.GetMyChartRequest
import com.ktigers20.mez.domain.api.usecase.GetMainChartUseCase
import com.ktigers20.mez.domain.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import timber.log.Timber

class MainPresenter(
    override val view: MainContract.View
    , private val getMainChartUseCase: GetMainChartUseCase
) : MainContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getAllBarChart() {
        getMainChartUseCase.getAllMainChartData().subscribe({
            it.body()?.let { allChartList ->
                view.setAllBarChart(allChartList)
            }
        }, {
            handleError(it)
            Timber.e(it.localizedMessage)
        }).addTo(compositeDisposable)
    }

    @SuppressLint("CheckResult")
    override fun getMyBarChart(userId: String) {
        getMainChartUseCase.getMyMainChartData(GetMyChartRequest(userId)).subscribe({
            it.body()?.let { myChartList ->
                view.setMyBarChart(myChartList)
            }
        }, {
            handleError(it)
            Timber.e(it.localizedMessage)
        }).addTo(compositeDisposable)

    }

    private fun handleError(throwable: Throwable) {
        if (throwable is HttpException) {
            val statusCode = throwable.code()
            if (statusCode == 400) {

            }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}
