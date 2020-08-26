package com.ktigers20.mez.domain.koin.modules

import com.ktigers20.mez.domain.api.usecase.GetBatchInfoUseCase
import com.ktigers20.mez.domain.api.usecase.GetPhoneBookInfoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPhoneBookInfoUseCase(get()) }
    factory { GetBatchInfoUseCase(get()) }
}
