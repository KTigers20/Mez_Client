package com.ktigers20.mez.data.entity

import com.google.gson.annotations.SerializedName


data class BatchInfo (
    @SerializedName("JOB_NAME") val jOB_NAME : String,
    @SerializedName("DEPT_NM") val dEPT_NM : String,
    @SerializedName("USER_NM") val uSER_NM : String,
    @SerializedName("STATUS") val sTATUS : String,
    @SerializedName("ORDER_TIME") val oRDER_TIME : String,
    @SerializedName("ORDER_ID") val oRDER_ID : String
) {
    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass) {
            return false
        }

        other as BatchInfo

        if(jOB_NAME != other.jOB_NAME) {
            return false
        }
        if(dEPT_NM != other.dEPT_NM) {
            return false
        }
        if(uSER_NM != other.uSER_NM) {
            return false
        }
        if(sTATUS != other.sTATUS) {
            return false
        }
        if(oRDER_TIME != other.oRDER_TIME) {
            return false
        }
        if(oRDER_ID != other.oRDER_ID) {
            return false
        }
        return true
    }
}
