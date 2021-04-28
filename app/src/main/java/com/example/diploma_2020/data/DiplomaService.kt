package com.example.diploma_2020.data

import com.example.diploma_2020.helpers.GenericResponse
import com.example.diploma_2020.helpers.NetworkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DiplomaService {
    @GET("/getPlaces.php")
    fun getPlaces(): Call<PlacesResponse>

    @GET("/getTraditional.php")
    fun getTraditional(): Call<PlacesResponse>

    @GET("/getStudyBars.php")
    fun getStudyBars(): Call<PlacesResponse>

    @GET("/getMonuments.php")
    fun getMonuments(): Call<MonumentsResponse>

    @GET("/getTimeSchedule.php")
    fun getTimeSchedule(@Query("id") placeId: String): Call<TimeScheduleResponse>

    @GET("/getTimeScheduleTraditional.php")
    fun getTimeScheduleTraditional(@Query("id") placeId: String): Call<TimeScheduleResponse>

    @GET("/getTimeScheduleStudyBars.php")
    fun getTimeScheduleStudyBars(@Query("id") placeId: String): Call<TimeScheduleResponse>

    @GET("/login.php")
    fun login(@Query("email") email: String, @Query("password") password: String): Call<LoginResponse>

    @GET("/insertUser.php")
    fun register(@Query("email") email: String, @Query("username") username: String, @Query("password") password: String): Call<RegisterResponse>
}