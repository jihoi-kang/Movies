package com.jay.movies.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class Genre(
    val id: Int,
    val name: String,
) : Parcelable