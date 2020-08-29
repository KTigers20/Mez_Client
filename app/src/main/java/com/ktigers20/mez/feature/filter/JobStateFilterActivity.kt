package com.ktigers20.mez.feature.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import com.ktigers20.mez.R
import com.ktigers20.mez.databinding.ActivityJobStateFilterBinding
import com.ktigers20.mez.singleton.SearchFilter

class JobStateFilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobStateFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        initView()
    }

    private fun setUpDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_job_state_filter)
        binding.activity = this
    }

    private fun initView() {
        binding.success.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                SearchFilter.status = "SUCCESS"
            }

        }

        binding.fail.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                SearchFilter.status = "FAIL"
            }
        }


    }
}