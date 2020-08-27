package com.ktigers20.mez.feature.searchDetail

import com.ktigers20.mez.data.entity.BatchDetailInfo

interface SearchDetailContract {
    interface View {
        fun setBatchDetailInfo(batchDetailInfo: BatchDetailInfo)
    }

    interface Presenter {
        val view: View

        fun getBatchDetailInfo(orderId: String)

        fun onCleared()
    }
}