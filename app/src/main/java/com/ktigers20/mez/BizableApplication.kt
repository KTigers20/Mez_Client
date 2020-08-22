package com.ktigers20.mez

import android.app.Application
import com.ktigers20.mez.domain.koin.modules.networkModule
import com.ktigers20.mez.domain.koin.modules.sharedPrefModule
import com.ktigers20.mez.domain.koin.modules.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BizableApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BizableApplication)
            modules(mutableListOf(sharedPrefModule, networkModule, useCaseModule))
        }
    }
}
