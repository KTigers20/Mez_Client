package com.ktigers20.mez.data.response

import com.google.gson.annotations.SerializedName

data class PhoneBookResponse(
//@SerializedName("id") var id: Long?,
    @SerializedName("DEPT_NM") var deptNm: String,
    @SerializedName("USER_NM") var userNm: String,
    @SerializedName("USER_HP") var userHp: String?,
    @SerializedName("USER_JOB") var userJob: String?
)
