package com.example.diploma_2020.ui.monuments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diploma_2020.data.Monument
import com.example.diploma_2020.data.Repository

class MonumentsViewModel(private val repo: Repository) : ViewModel() {
    private val _monuments = MutableLiveData<List<Monument>>()
    val monuments: LiveData<List<Monument>> = _monuments


    fun getMonuments() {
        repo.getMonuments()

        repo.monuments.observeForever {
            _monuments.value = it
        }
    }

}