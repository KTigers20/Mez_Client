package com.ktigers20.mez.data.entity

import com.google.gson.annotations.SerializedName

//data class PhoneBookInfo(
////    @SerializedName("id") var id: Long,
////    @SerializedName("user_nm") var userNm: String,
////    @SerializedName("dept_name") var deptName: String,
////    @SerializedName("team_name") var teamName: String,
////    @SerializedName("phone_number") var phoneNumber: String
//    @SerializedName("DEPT_NM") var deptNm: String,
//    @SerializedName("USER_NM") var userNm: String,
//    @SerializedName("USER_HP") var userHp: String?,
//    @SerializedName("USER_JOB") var userJob: String?
//)

data class PhoneBookInfo(

    @SerializedName("DEPT_NM") val dEPT_NM: String,
    @SerializedName("USER_NM") val uSER_NM: String,
    @SerializedName("USER_HP") val uSER_HP: String?,
    @SerializedName("USER_JOB") val uSER_JOB: String?,
    @SerializedName("USER_CD") val uSER_CD : Int
) {
    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass) {
            return false
        }

        other as PhoneBookInfo

        if(uSER_CD != other.uSER_CD) {
            return false
        }
        if(uSER_JOB != other.uSER_JOB) {
            return false
        }
        if(uSER_HP != other.uSER_HP) {
            return false
        }
        if(uSER_NM != other.uSER_NM) {
            return false
        }
        if(dEPT_NM != other.dEPT_NM) {
            return false
        }
        return true
    }
}


