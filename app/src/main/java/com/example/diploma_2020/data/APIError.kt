package com.example.diploma_2020.data

import com.google.gson.annotations.SerializedName

data class APIError(
    @SerializedName("status")
    val status: String,
    @SerializedName("error")
    val message: String
)