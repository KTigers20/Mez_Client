package com.ktigers20.mez.data.response

import com.google.gson.annotations.SerializedName
import com.ktigers20.mez.data.entity.Pageable
import com.ktigers20.mez.data.entity.PhoneBookInfo


data class PhoneBookResponse(
    @SerializedName("contents") var contents: ArrayList<PhoneBookInfo>,
    @SerializedName("pageable") var pageable: Pageable
)
