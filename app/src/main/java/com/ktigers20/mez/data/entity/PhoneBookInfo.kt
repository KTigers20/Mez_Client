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
    @SerializedName("USER_JOB") val uSER_JOB: String?
)
