package com.example.easycode.domain.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("data")
    val data: Data? = null,
    @SerializedName("state")
    val state: String? = null,
    @SerializedName("status_msg")
    val statusMsg: String? = null
)