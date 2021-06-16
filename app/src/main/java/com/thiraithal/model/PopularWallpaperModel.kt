package com.thiraithal.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "posts",
    indices = [Index(value = ["id"], unique = false)])

@Parcelize
data class PopularWallpaperModel
    (val id : String, val wallpaperName: String, val imageUrl: String, val favourite: Boolean, val desc: String) : Parcelable

