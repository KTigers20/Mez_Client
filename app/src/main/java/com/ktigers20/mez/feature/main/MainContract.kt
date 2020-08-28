package com.ktigers20.mez.feature.main

import com.ktigers20.mez.data.entity.MainBarChartInfo
import com.ktigers20.mez.data.response.GetAllChartResponse
import com.ktigers20.mez.data.response.GetMyChartResponse

interface MainContract {
    interface View {
        fun setMyBarChart(mainBarChartInfo: ArrayList<GetMyChartResponse>)

        fun setAllBarChart(mainBarChartInfo: ArrayList<GetAllChartResponse>)
    }

    interface Presenter {
        val view: View

        fun getMyBarChart(userId: String)

        fun getAllBarChart()

        fun onCleared()
    }
}
