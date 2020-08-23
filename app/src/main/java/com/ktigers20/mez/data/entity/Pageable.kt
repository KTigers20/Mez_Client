package com.ktigers20.mez.data.entity

import com.google.gson.annotations.SerializedName

data class Pageable(
    @SerializedName("totalPage") val totalPage: Int
)
