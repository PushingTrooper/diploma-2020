package com.example.diploma_2020.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diploma_2020.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val context: Context, private val service: DiplomaService) {
    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = _places

    private val _traditional = MutableLiveData<List<Place>>()
    val traditional: LiveData<List<Place>> = _traditional

    private val _studyBars = MutableLiveData<List<Place>>()
    val studyBars: LiveData<List<Place>> = _studyBars

    private val _monuments = MutableLiveData<List<Monument>>()
    val monuments: LiveData<List<Monument>> = _monuments

    private val _timeSchedule = MutableLiveData<TimeScheduleResponse>()
    val timeSchedule: LiveData<TimeScheduleResponse> = _timeSchedule

    private val _traditionalTimeSchedule = MutableLiveData<TimeScheduleResponse>()
    val traditionalTimeSchedule: LiveData<TimeScheduleResponse> = _traditionalTimeSchedule

    private val _studyBarsTimeSchedule = MutableLiveData<TimeScheduleResponse>()
    val studyBarsTimeSchedule: LiveData<TimeScheduleResponse> = _studyBarsTimeSchedule

    fun getPlaces() {
        service.getPlaces().enqueue(object : Callback<PlacesResponse> {
            override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                _places.value = listOf()
            }

            override fun onResponse(
                call: Call<PlacesResponse>,
                response: Response<PlacesResponse>
            ) {
                if (response.isSuccessful) {
                    _places.value = response.body()
                } else {
                    _places.value = listOf()
                }
            }
        })
    }

    fun getTraditional() {
        service.getTraditional().enqueue(object : Callback<PlacesResponse> {
            override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                _traditional.value = listOf()
            }

            override fun onResponse(
                call: Call<PlacesResponse>,
                response: Response<PlacesResponse>
            ) {
                if (response.isSuccessful) {
                    _traditional.value = response.body()
                } else {
                    _traditional.value = listOf()
                }
            }
        })
    }

    fun getStudyBars() {
        service.getStudyBars().enqueue(object : Callback<PlacesResponse> {
            override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                _studyBars.value = listOf()
            }

            override fun onResponse(
                call: Call<PlacesResponse>,
                response: Response<PlacesResponse>
            ) {
                if (response.isSuccessful) {
                    _studyBars.value = response.body()
                } else {
                    _studyBars.value = listOf()
                }
            }
        })
    }

    fun getMonuments() {
        service.getMonuments().enqueue(object : Callback<MonumentsResponse> {
            override fun onFailure(call: Call<MonumentsResponse>, t: Throwable) {
                _monuments.value = listOf()
            }

            override fun onResponse(
                call: Call<MonumentsResponse>,
                response: Response<MonumentsResponse>
            ) {
                if (response.isSuccessful) {
                    _monuments.value = response.body()
                } else {
                    _monuments.value = listOf()
                }
            }
        })
    }

    fun getTimeSchedule(id: String) {
        service.getTimeSchedule(id).enqueue(object : Callback<TimeScheduleResponse> {
            override fun onFailure(call: Call<TimeScheduleResponse>, t: Throwable) {
                _timeSchedule.value = TimeScheduleResponse(listOf(), context.getString(R.string.there_is_a_problem_with_the_connection))
            }

            override fun onResponse(
                call: Call<TimeScheduleResponse>,
                response: Response<TimeScheduleResponse>
            ) {
                if (response.isSuccessful) {
                    _timeSchedule.value = response.body()
                } else {
                    _timeSchedule.value = TimeScheduleResponse(listOf(), context.getString(R.string.there_is_a_problem_with_the_connection))
                }
            }
        })
    }

    fun getTimeScheduleTraditional(id: String) {
        service.getTimeScheduleTraditional(id).enqueue(object : Callback<TimeScheduleResponse> {
            override fun onFailure(call: Call<TimeScheduleResponse>, t: Throwable) {
                _traditionalTimeSchedule.value = TimeScheduleResponse(listOf(), context.getString(R.string.there_is_a_problem_with_the_connection))
            }

            override fun onResponse(
                call: Call<TimeScheduleResponse>,
                response: Response<TimeScheduleResponse>
            ) {
                if (response.isSuccessful) {
                    _traditionalTimeSchedule.value = response.body()
                } else {
                    _traditionalTimeSchedule.value = TimeScheduleResponse(listOf(), context.getString(R.string.there_is_a_problem_with_the_connection))
                }
            }
        })
    }

    fun getStudyBarsTimeSchedule(id: String) {
        service.getTimeScheduleTraditional(id).enqueue(object : Callback<TimeScheduleResponse> {
            override fun onFailure(call: Call<TimeScheduleResponse>, t: Throwable) {
                _studyBarsTimeSchedule.value = TimeScheduleResponse(listOf(), context.getString(R.string.there_is_a_problem_with_the_connection))
            }

            override fun onResponse(
                call: Call<TimeScheduleResponse>,
                response: Response<TimeScheduleResponse>
            ) {
                if (response.isSuccessful) {
                    _studyBarsTimeSchedule.value = response.body()
                } else {
                    _studyBarsTimeSchedule.value = TimeScheduleResponse(listOf(), context.getString(R.string.there_is_a_problem_with_the_connection))
                }
            }
        })
    }

    fun login(username: String, password: String) = service.login(username, password)

    fun register(email: String, username: String, password: String) = service.register(email, username, password)
}