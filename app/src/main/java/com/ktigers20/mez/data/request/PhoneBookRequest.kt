package com.ktigers20.mez.data.request

import com.google.gson.annotations.SerializedName

data class PhoneBookRequest(
    @SerializedName("user_nm") var userName: String?,
    @SerializedName("user_job") var userJob: String?
)
