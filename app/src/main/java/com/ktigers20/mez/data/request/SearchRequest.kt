package com.ktigers20.mez.data.request

import com.google.gson.annotations.SerializedName

data class SearchRequest (
    @SerializedName("PAGE_NUM") val pAGE_NUM : Long,
    @SerializedName("DEPT_NM") val dEPT_NM : String,
    @SerializedName("USER_NM") val uSER_NM : String,
    @SerializedName("JOB_NAME") val jOB_NAME : String,
    @SerializedName("STATUS") val sTATUS : String,
    @SerializedName("APPLICATION") val aPPLICATION : String,
    @SerializedName("GROUP_NAME") val gROUP_NAME : String,
    @SerializedName("START_DATE") val sTART_DATE : String,
    @SerializedName("END_DATE") val eND_DATE : String
)
