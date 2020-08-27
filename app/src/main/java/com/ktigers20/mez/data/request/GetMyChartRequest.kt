package com.ktigers20.mez.data.request

import com.google.gson.annotations.SerializedName

data class GetMyChartRequest(
    @SerializedName("USER_ID") var uSER_ID: String
)
