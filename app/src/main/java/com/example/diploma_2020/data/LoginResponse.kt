package com.example.diploma_2020.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val email: String,
    val username: String,
    val status: String,
    @SerializedName("error")
    val message: String
)