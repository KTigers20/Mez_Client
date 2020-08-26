package com.ktigers20.mez.feature.batchDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ktigers20.mez.R

class BatchDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batch_detail)
    }

    override fun onBackPressed() {
        finish()
    }
}