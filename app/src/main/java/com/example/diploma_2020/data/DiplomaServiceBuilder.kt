package com.example.diploma_2020.data

import com.example.diploma_2020.helpers.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DiplomaServiceBuilder {

    private fun client() = OkHttpClient.Builder()
        .build()

    private fun retrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client())
        .build()

    fun <T> buildService(
        service: Class<T>
    ): T = retrofit().create(service)

}