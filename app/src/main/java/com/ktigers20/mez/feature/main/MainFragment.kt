package com.ktigers20.mez.feature.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.activity_search_detail.view.*


class MainFragment : Fragment(), MainContract.View {
    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var presenter: MainPresenter

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = MainPresenter(this)
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
        setUpChart()


    }

    private fun setUpChart() {
        val horizontalBarChart = mainBinding.horizontalBarChart
        val noOfBatch = ArrayList<BarEntry>()
        noOfBatch.add(BarEntry(348f, 0))
        noOfBatch.add(BarEntry(50f, 1))
        noOfBatch.add(BarEntry(10f, 2))

        noOfBatch.add(BarEntry(10f, 3))
        noOfBatch.add(BarEntry(10f, 4))
        noOfBatch.add(BarEntry(10f, 5))

        noOfBatch.add(BarEntry(10f, 6))
        noOfBatch.add(BarEntry(10f, 7))
        noOfBatch.add(BarEntry(10f, 8))


        val category = ArrayList<String>()
        category.add("Waiting")
        category.add("Fail")
        category.add("Success")

        category.add("Waiting")
        category.add("Fail")
        category.add("Success")

        category.add("Waiting")
        category.add("Fail")
        category.add("Success")


        var barDataSet = BarDataSet(noOfBatch, "batch")
        horizontalBarChart.animateY(1000)
        var data = BarData(category, barDataSet)
        barDataSet.colors =
            mutableListOf(
                Color.parseColor("#C2C7CF"),
                Color.parseColor("#FF8181"),
                Color.parseColor("#6FCF97")
            )

        horizontalBarChart.data = data
        horizontalBarChart.setDescription("")
    }
}