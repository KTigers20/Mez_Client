package com.ktigers20.mez.feature.login

import android.annotation.SuppressLint
import com.ktigers20.mez.data.request.LoginRequest
import com.ktigers20.mez.domain.api.usecase.GetAccessTokenUseCase
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.koin.repository.SharedPrefRepository
import com.ktigers20.mez.domain.utils.addTo
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter(
    override val view: LoginContract.View
    , private val getAccessTokenUseCase: GetAccessTokenUseCase
    , private val sharedPrefRepository: SharedPrefRepository
) : LoginContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getAccessToken(id: String, password: String) {
        getAccessTokenUseCase.getAccessToken(LoginRequest(id, password)).subscribe({
            if (it.code() == 200) {
                sharedPrefRepository.writePrefs(Consts.AUTO_LOGIN, true)
                sharedPrefRepository.writePrefs(Consts.USER_ID, id)
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
