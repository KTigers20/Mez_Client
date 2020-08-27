package com.ktigers20.mez.data.entity

import com.google.gson.annotations.SerializedName

data class MainBarChartInfo(
    var cNT_OPER_success: Int,
    var cNT_INFO_success: Int,
    var cNT_EDW_success: Int,

    var cNT_OPER_fail: Int,
    var cNT_EDW_fail: Int,
    var cNT_INFO_fail: Int
)
