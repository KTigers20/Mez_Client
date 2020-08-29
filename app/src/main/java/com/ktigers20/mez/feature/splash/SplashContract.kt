package com.ktigers20.mez.feature.splash

interface SplashContract {
    interface View {
        fun animation()

        fun navigateToLogin()

        fun navigateToMain()
    }

    interface Presenter {
        val view: View

        fun initDelay()

        fun onCleared()
    }
}