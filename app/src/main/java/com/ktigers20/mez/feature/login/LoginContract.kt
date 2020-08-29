package com.ktigers20.mez.feature.login

import com.ktigers20.mez.data.response.GetAllChartResponse
import com.ktigers20.mez.data.response.GetMyChartResponse

interface LoginContract {
    interface View {
        fun navigateToMainPage(token: String)

        fun toastError()

    }

    interface Presenter {
        val view: View

        fun getAccessToken(id: String, password: String)

        fun onCleared()
    }
}