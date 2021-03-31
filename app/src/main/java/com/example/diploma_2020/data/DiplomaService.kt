package com.example.diploma_2020.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DiplomaService {
    @GET("/getPlaces.php")
    fun getPlaces(): Call<PlacesResponse>

    @GET("/getMonuments.php")
    fun getMonuments(): Call<MonumentsResponse>

    @GET("/getTimeSchedule.php")
    fun getTimeSchedule(@Query("id") placeId: String): Call<TimeScheduleResponse>

    @GET("/login.php")
    fun login(@Query("email") email: String, @Query("password") password: String): Call<LoginResponse>
}