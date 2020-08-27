package com.ktigers20.mez.data.response

import com.google.gson.annotations.SerializedName

data class GetAllChartResponse(
    @SerializedName("ODATE") var oDATE: Int,
    @SerializedName("OPERATION_STATUS") var oPERATION_STATUS: String,
    @SerializedName("CNT_OPERATION") var cNT_OPERATION: Int,
    @SerializedName("INFORMATION_STATUS") var iNFORMATION_STATUS: String,
    @SerializedName("CNT_INFORMATION") var cNT_INFORMATION: Int,
    @SerializedName("EDW_STATUS") var eDW_STATUS: String,
    @SerializedName("CNT_EDW") var cNT_EDW: Int,
    @SerializedName("ETC_STATUS") var eTC_STATUS: String,
    @SerializedName("CNT_ETC") var cNT_ETC: Int
)