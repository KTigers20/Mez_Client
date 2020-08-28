package com.ktigers20.mez.data.request

import com.google.gson.annotations.SerializedName

data class PersonInfoByNameRequest(
    @SerializedName("USER_NM") var userName: String,
    @SerializedName("PAGE_NUM") var pageNum: Long
)
