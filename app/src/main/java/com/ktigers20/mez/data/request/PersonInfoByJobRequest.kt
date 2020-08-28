package com.ktigers20.mez.data.request

import android.app.job.JobInfo
import com.google.gson.annotations.SerializedName

data class PersonInfoByJobRequest (
    @SerializedName("USER_JOB")var userJob: String,
    @SerializedName("PAGE_NUM")var pageNum: Long
)
