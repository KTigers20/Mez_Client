package com.ktigers20.mez.data.response

import com.google.gson.annotations.SerializedName

data class SearchDetailResponse(
    @SerializedName("JOB_NAME") var jOB_NAME: String,
    @SerializedName("USER1DEPT") var uSER1DEPT: String?,
    @SerializedName("USER1NAME") var uSER1NAME: String?,
    @SerializedName("USER1HP") var uSER1HP: String?,
    @SerializedName("USER2DEPT") var uSER2DEPT: String?,
    @SerializedName("USER2NAME") var uSER2NAME: String?,
    @SerializedName("USER2HP") var uSER2HP: String?,
    @SerializedName("USER3DEPT") var uSER3DEPT: String?,
    @SerializedName("USER3NAME") var uSER3NAME: String?,
    @SerializedName("USER3HP") var uSER3HP: String?,
    @SerializedName("USER4DEPT") var uSER4DEPT: String?,
    @SerializedName("USER4NAME") var uSER4NAME: String?,
    @SerializedName("USER4HP") var uSER4HP: String?,
    @SerializedName("ORDER_TIME") var oRDER_TIME: String,
    @SerializedName("APPLICATION") var aPPLICATION: String,
    @SerializedName("GROUP_NAME") var gROUP_NAME: String,
    @SerializedName("DESCRIPTION") var dESCRIPTION: String,
    @SerializedName("NODE_ID") var nODE_ID: String,
    @SerializedName("CMD_LINE") var cMD_LINE: String?,
    @SerializedName("STATUS") var sTATUS: String,
    @SerializedName("ERROR_DESCRIPTION") var eRROR_DESCRIPTION: String
)
