package com.example.diploma_2020.data

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    val status: String,
    @SerializedName("error")
    val message: String
)