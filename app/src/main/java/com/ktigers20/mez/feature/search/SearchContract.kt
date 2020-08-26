package com.ktigers20.mez.feature.search

import com.ktigers20.mez.data.entity.BatchInfo
import com.ktigers20.mez.singleton.SearchFilter

interface SearchContract {
    interface View {
        fun setBatchInfoList(batchInfoList: ArrayList<BatchInfo>)

        fun setSearchPageIsEnd(isEnd: Boolean)
    }

    interface Presenter {
        val view: View

        fun getBatchInfo(searchFilter: SearchFilter, pageNum: Long)

        fun onCleared()
    }
}
