package com.example.diploma_2020.ui.traditional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diploma_2020.data.Place
import com.example.diploma_2020.data.Repository
import com.example.diploma_2020.data.TimeScheduleResponse

class TraditionalViewModel(private val repo: Repository) : ViewModel() {
    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = _places

    private val _timeSchedule = MutableLiveData<TimeScheduleResponse>()
    val timeSchedule: LiveData<TimeScheduleResponse> = _timeSchedule

    fun getPlaces() {
        repo.getTraditional()

        repo.traditional.observeForever {
            _places.value = it
        }
    }

    fun getTimeSchedule(id: String) {
        repo.getTimeScheduleTraditional(id)

        repo.traditionalTimeSchedule.observeForever {
            _timeSchedule.value = it
        }
    }
}