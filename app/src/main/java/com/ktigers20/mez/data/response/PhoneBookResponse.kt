package com.ktigers20.mez.data.response

import com.google.gson.annotations.SerializedName
import com.ktigers20.mez.data.entity.Pageable
import com.ktigers20.mez.data.entity.PhoneBookInfo


data class PhoneBookResponse(
    @SerializedName("contents") val contents: ArrayList<PhoneBookInfo>,
    @SerializedName("pageable") val pageable: Pageable
)
