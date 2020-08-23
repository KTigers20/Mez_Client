package com.ktigers20.mez.domain.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

infix fun Disposable.addTo(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)
