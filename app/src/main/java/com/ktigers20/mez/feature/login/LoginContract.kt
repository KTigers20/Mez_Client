package com.ktigers20.mez.feature.login

interface LoginContract {
    interface View {
        fun navigateToMainPage()

        fun toastError()

    }

    interface Presenter {
        val view: View

        fun getAccessToken(id: String, password: String)

        fun onCleared()
    }
}