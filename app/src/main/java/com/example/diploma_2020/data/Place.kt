package com.example.diploma_2020.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
    val address: String,
    val id: String,
    val imageUrl: String,
    val name: String,
    val rating: String,
    val type: String,
    val description: String
): Parcelable