package com.ktigers20.mez.data.response

import com.google.gson.annotations.SerializedName
import com.ktigers20.mez.data.entity.BatchInfo
import com.ktigers20.mez.data.entity.Pageable

data class SearchResponse (
    @SerializedName("contents") var contents : ArrayList<BatchInfo>,
    @SerializedName("pageable") var pageable : Pageable
)
