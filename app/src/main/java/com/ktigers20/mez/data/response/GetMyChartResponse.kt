package com.ktigers20.mez.data.response

import com.google.gson.annotations.SerializedName

data class GetMyChartResponse(
    @SerializedName("USER_CD") var uSER_CD: Int,
    @SerializedName("USER_ID") var uSER_ID: String,
    @SerializedName("STATUS") var sTATUS: String,
    @SerializedName("CNT_OPER") var cNT_OPER: Int,
    @SerializedName("CNT_INFO") var cNT_INFO: Int,
    @SerializedName("CNT_EDW") var cNT_EDW: Int,
    @SerializedName("CNT_ETC") var cNT_ETC: Int
)
