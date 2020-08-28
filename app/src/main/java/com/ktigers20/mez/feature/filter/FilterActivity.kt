package com.ktigers20.mez.feature.filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.ActivityFilterBinding
import com.ktigers20.mez.singleton.SearchFilter

class FilterActivity : AppCompatActivity() {
    private lateinit var filterBinding: ActivityFilterBinding
    private lateinit var filterAdapter: FilterAdapter

    private lateinit var filterList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        initView()
    }

    private fun setUpDataBinding() {
        filterBinding = DataBindingUtil.setContentView(this, R.layout.activity_filter)
        filterBinding.activity = this
    }

    private fun initView() {
        filterList = ArrayList()
        filterList.add("담당자명")
        filterList.add("소속부서")
        filterList.add("작업명")
        filterList.add("작업상태")
        filterList.add("작업일시")
        filterList.add("어플리케이션")
        filterList.add("작업그룹")

        filterAdapter = FilterAdapter(filterList, filterItemClicked)
        filterBinding.filterRecycler.adapter = filterAdapter
        filterBinding.filterRecycler.apply {
            adapter = filterAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

    private val filterItemClicked = { title:String ->
        startActivity(Intent(this, JobStateFilterActivity::class.java).apply {

        })

    }

    fun initSearchFilter() {
        SearchFilter.userName = ""
        SearchFilter.application = ""
        SearchFilter.endDate = ""
        SearchFilter.groupName = ""
        SearchFilter.status = ""
        SearchFilter.jobName =""
        SearchFilter.startDate = ""
        SearchFilter.deptName = ""
    }
}
