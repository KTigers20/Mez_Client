package com.ktigers20.mez.data.request

import com.google.gson.annotations.SerializedName

data class SearchDetailRequest(
    @SerializedName("ORDER_ID") var orderId: String
)
