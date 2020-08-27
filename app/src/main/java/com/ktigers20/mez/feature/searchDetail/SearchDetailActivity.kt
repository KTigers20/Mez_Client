package com.ktigers20.mez.feature.searchDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ktigers20.mez.R
import com.ktigers20.mez.data.entity.BatchDetailInfo
import com.ktigers20.mez.databinding.ActivitySearchDetailBinding
import com.ktigers20.mez.domain.globalconst.Consts
import org.koin.android.ext.android.get

class SearchDetailActivity : AppCompatActivity(), SearchDetailContract.View {
    lateinit var binding: ActivitySearchDetailBinding
    lateinit var presenter: SearchDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        initView()
    }

    private fun setUpDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_detail)
        binding.activity = this
    }

    private fun initView() {
        presenter = SearchDetailPresenter(this, get())
        val orderId: String? = intent.getStringExtra(Consts.ORDER_ID)
        presenter.getBatchDetailInfo(orderId!!)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun setBatchDetailInfo(batchDetailInfo: BatchDetailInfo) {
        val orderDate = parseDateString(batchDetailInfo.oRDER_TIME)

        binding.batchTitle.text = batchDetailInfo.jOB_NAME
        binding.description.text = "(" + batchDetailInfo.dESCRIPTION + ")"

        binding.p1UserInfoTv.text = "담당자1 : " + batchDetailInfo.uSER1NAME + "/" + batchDetailInfo.uSER1DEPT
        if(batchDetailInfo.uSER1HP != null) {
            binding.p1UserHp.text = batchDetailInfo.uSER1HP
            binding.callToP1.visibility = View.VISIBLE
        }

        if(batchDetailInfo.uSER2NAME != null && batchDetailInfo.uSER2DEPT != null) {
            binding.p2UserInfoTv.text = "담당자2 : " + batchDetailInfo.uSER2NAME + "/" + batchDetailInfo.uSER2DEPT
        }
        if(batchDetailInfo.uSER2HP != null) {
            binding.p2UserHp.text = batchDetailInfo.uSER2HP
            binding.callToP2.visibility = View.VISIBLE
        }
        binding.orderTimeContent.text = orderDate
        binding.appCodeContent.text = batchDetailInfo.aPPLICATION
        binding.groupContent.text = batchDetailInfo.gROUP_NAME
        binding.errorContent.text = batchDetailInfo.cMD_LINE
        binding.errorActionContent.text = batchDetailInfo.eRROR_DESCRIPTION
    }

    private fun parseDateString(dateStr: String): String {
        val year = dateStr.substring(0,4)
        val month = dateStr.substring(4,6)
        val day = dateStr.substring(6,8)
        val hour = dateStr.substring(8,10)
        val minute = dateStr.substring(10,12)
        val second = dateStr.substring(12)
        return "$year/$month/$day $hour:$minute:$second"
    }

    override fun onBackPressed() {
        finish()
    }
}
