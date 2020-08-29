package com.ktigers20.mez.domain.koin.modules

import com.ktigers20.mez.domain.api.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPhoneBookInfoUseCase(get()) }

    factory { GetBatchInfoUseCase(get()) }

    factory { GetBatchDetailInfoUseCase(get()) }

    factory { GetMainChartUseCase(get()) }

    factory { GetAccessTokenUseCase(get()) }
}
