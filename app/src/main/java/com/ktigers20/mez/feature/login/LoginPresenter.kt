package com.ktigers20.mez.feature.login

import android.annotation.SuppressLint
import android.util.Log
import com.ktigers20.mez.data.request.LoginRequest
import com.ktigers20.mez.domain.api.usecase.GetAccessTokenUseCase
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.koin.repository.SharedPrefRepository
import com.ktigers20.mez.domain.utils.ConverterUtil
import com.ktigers20.mez.domain.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONException
import org.json.JSONObject

class LoginPresenter(
    override val view: LoginContract.View
    , private val getAccessTokenUseCase: GetAccessTokenUseCase
    , private val sharedPrefRepository: SharedPrefRepository
) : LoginContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun getAccessToken(id: String, password: String) {
        getAccessTokenUseCase.getAccessToken(LoginRequest(id, password)).subscribe({
            val response = JSONObject(it.toString())
            var accessToken = ""
            try {
                accessToken = response.getString("token")
            } catch ( e : JSONException) {
                view.toastError()
                e.printStackTrace()
            }
            if (accessToken.isNotEmpty()) {
                sharedPrefRepository.writePrefs(Consts.AUTO_LOGIN, true)
                sharedPrefRepository.writePrefs(Consts.USER_ID, id)
                sharedPrefRepository.writePrefs(
                    Consts.ACCESS_TOKEN,
                    ConverterUtil._Encode(accessToken)
                )
                view.navigateToMainPage()
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
