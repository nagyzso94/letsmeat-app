package com.letsmeatapp.letsmeatapp.data.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AvgReview(
    val cleanness: Double,
    val prices: Double,
    val savouriness: Double,
    val service: Double
) : Parcelable