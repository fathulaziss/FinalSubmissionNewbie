package com.example.finalsubmissionnewbie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
