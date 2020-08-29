package com.ktigers20.mez.feature.splash

import android.os.Handler
import com.ktigers20.mez.domain.api.usecase.GetAccessTokenUseCase
import com.ktigers20.mez.domain.globalconst.Consts
import com.ktigers20.mez.domain.koin.repository.SharedPrefRepository
import io.reactivex.disposables.CompositeDisposable

class SplashPresenter(
    override val view: SplashContract.View,
    private val sharedPrefRepository: SharedPrefRepository
) : SplashContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun initDelay() {
        Handler().postDelayed(AnimationHandler(), 800)
    }

    inner class AnimationHandler : Runnable {
        override fun run() {
            view.animation()
            Handler().postDelayed(SplashHandler(), 2800)
        }
    }

    inner class SplashHandler : Runnable {
        override fun run() {
            if (sharedPrefRepository.getPrefsBooleanValue(Consts.AUTO_LOGIN, false)) {
                view.navigateToMain()
            } else
                view.navigateToLogin()
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
