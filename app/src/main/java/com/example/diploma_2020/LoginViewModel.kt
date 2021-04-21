package com.example.diploma_2020

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diploma_2020.data.LoginResponse
import com.example.diploma_2020.data.Repository
import com.example.diploma_2020.helpers.GenericResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repo: Repository): ViewModel() {
    fun login(username: String, password: String) = repo.login(username, password)
}