package com.ktigers20.mez.feature.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.tabs.TabLayout
import com.ktigers20.mez.data.entity.MainBarChartInfo
import com.ktigers20.mez.data.response.GetAllChartResponse
import com.ktigers20.mez.data.response.GetMyChartResponse
import com.ktigers20.mez.databinding.FragmentMainBinding
import com.ktigers20.mez.singleton.MainViewMode
import org.koin.android.ext.android.get


class MainFragment : Fragment(), MainContract.View {
    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var presenter: MainPresenter

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = MainPresenter(this, get())
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpDataBinding(inflater, container)
        initView()
        return mainBinding.root
    }

    private fun setUpDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        mainBinding.fragment = this
    }

    private fun initView() {
        if (MainViewMode.mode == "MY") {
            val tab = mainBinding.mainTabLayout.getTabAt(0)
            tab?.select()
            presenter.getMyBarChart("SE12055")

        } else {
            val tab = mainBinding.mainTabLayout.getTabAt(1)
            tab?.select()
            presenter.getAllBarChart()

        }
        mainBinding.mainTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        presenter.getMyBarChart("SE12055")
                    }
                    1 -> {
                        presenter.getAllBarChart()
                    }
                }
            }

        })
    }

    override fun setMyBarChart(mainBarChartInfo: ArrayList<GetMyChartResponse>) {
        var edwSuccessCnt = 0
        var operSuccessCnt = 0
        var infoSuccessCnt = 0
        var edwFailCnt = 0
        var operFailCnt = 0
        var infoFailCnt = 0

        for (chartInfo in mainBarChartInfo) {
            if (chartInfo.sTATUS.trim() == "SUCCESS") {
                edwSuccessCnt += chartInfo.cNT_EDW
                operSuccessCnt += chartInfo.cNT_OPER
                infoSuccessCnt += chartInfo.cNT_INFO
            } else if (chartInfo.sTATUS.trim() == "FAIL") {
                edwFailCnt += chartInfo.cNT_EDW
                operFailCnt += chartInfo.cNT_OPER
                infoFailCnt += chartInfo.cNT_INFO
            }
        }
        setUpChart(
            MainBarChartInfo(
                operSuccessCnt,
                infoSuccessCnt,
                edwSuccessCnt,
                operFailCnt,
                infoFailCnt,
                infoFailCnt
            )
        )
    }

    override fun setAllBarChart(mainBarChartInfo: ArrayList<GetAllChartResponse>) {
        var edwSuccessCnt = 0
        var operSuccessCnt = 0
        var infoSuccessCnt = 0
        var edwFailCnt = 0
        var operFailCnt = 0
        var infoFailCnt = 0

        for (chartInfo in mainBarChartInfo) {
            if (chartInfo.eDW_STATUS.trim() == "SUCCESS") {
                edwSuccessCnt += chartInfo.cNT_EDW
                operSuccessCnt += chartInfo.cNT_OPERATION
                infoSuccessCnt += chartInfo.cNT_INFORMATION
            } else if (chartInfo.eDW_STATUS.trim() == "FAIL") {
                edwFailCnt += chartInfo.cNT_EDW
                operFailCnt += chartInfo.cNT_OPERATION
                infoFailCnt += chartInfo.cNT_INFORMATION
            }
        }
        setUpChart(
            MainBarChartInfo(
                operSuccessCnt,
                infoSuccessCnt,
                edwSuccessCnt,
                operFailCnt,
                infoFailCnt,
                infoFailCnt
            )
        )
    }

    private fun setUpChart(mainPageChartInfo: MainBarChartInfo) {
        val horizontalBarChart = mainBinding.horizontalBarChart
        val noOfBatch = ArrayList<BarEntry>()
        noOfBatch.add(BarEntry(mainPageChartInfo.cNT_INFO_fail.toFloat(), 0))
        noOfBatch.add(BarEntry(mainPageChartInfo.cNT_INFO_success.toFloat(), 1))

        noOfBatch.add(BarEntry(mainPageChartInfo.cNT_OPER_fail.toFloat(), 2))
        noOfBatch.add(BarEntry(mainPageChartInfo.cNT_OPER_success.toFloat(), 3))

        noOfBatch.add(BarEntry(mainPageChartInfo.cNT_EDW_fail.toFloat(), 4))
        noOfBatch.add(BarEntry(mainPageChartInfo.cNT_EDW_success.toFloat(), 5))


        val category = ArrayList<String>()
        category.add("Fail")
        category.add("Success")

        category.add("Fail")
        category.add("Success")

        category.add("Fail")
        category.add("Success")


        val barDataSet = BarDataSet(noOfBatch, "batch")
        horizontalBarChart.animateY(1000)
        val data = BarData(category, barDataSet)
        barDataSet.colors =
            mutableListOf(
                Color.parseColor("#FF8181"),
                Color.parseColor("#6FCF97")
            )

        horizontalBarChart.data = data
        horizontalBarChart.setDescription("")
    }

}