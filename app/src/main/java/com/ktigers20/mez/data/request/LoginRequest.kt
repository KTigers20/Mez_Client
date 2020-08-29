package com.ktigers20.mez.data.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("uid") var uid: String,
    @SerializedName("password") var password: String
)
