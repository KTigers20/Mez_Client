package com.ktigers20.mez.data.request

import com.google.gson.annotations.SerializedName

data class SearchRequest (
    @SerializedName("PAGE_NUM") var pAGE_NUM : Long,
    @SerializedName("DEPT_NM") var dEPT_NM : String,
    @SerializedName("USER_NM") var uSER_NM : String,
    @SerializedName("JOB_NAME") var jOB_NAME : String,
    @SerializedName("STATUS") var sTATUS : String,
    @SerializedName("APPLICATION") var aPPLICATION : String,
    @SerializedName("GROUP_NAME") var gROUP_NAME : String,
    @SerializedName("START_DATE") var sTART_DATE : String,
    @SerializedName("END_DATE") var eND_DATE : String
)
