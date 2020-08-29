package com.ktigers20.mez.feature.login

import android.annotation.SuppressLint
import com.ktigers20.mez.data.request.LoginRequest
import com.ktigers20.mez.domain.api.usecase.GetAccessTokenUseCase
import com.ktigers20.mez.domain.api.usecase.GetMainChartUseCase
import com.ktigers20.mez.domain.utils.addTo
import com.ktigers20.mez.feature.main.MainContract
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class LoginPresenter(
    override val view: LoginContract.View
    , private val getAccessTokenUseCase: GetAccessTokenUseCase
): LoginContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getAccessToken(id: String, password: String) {
        getAccessTokenUseCase.getAccessToken(LoginRequest(id, password)).subscribe({
            if(it.code() == 200) {
                view.navigateToMainPage(it.body()!!.token)
            } else {
                view.toastError()
            }
        }, {
            view.toastError()
        }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
