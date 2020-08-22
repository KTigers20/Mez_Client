package com.ktigers20.mez.data.entity

import com.google.gson.annotations.SerializedName

data class PhoneBookInfo(
    @SerializedName("id") var id: Long,
    @SerializedName("user_nm") var userNm: String,
    @SerializedName("dept_name") var deptName: String,
    @SerializedName("team_name") var teamName: String,
    @SerializedName("phone_number") var phoneNumber: String
)
