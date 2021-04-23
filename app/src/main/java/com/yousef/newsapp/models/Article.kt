package com.yousef.newsapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
     @PrimaryKey val publishedAt: String,
     val author: String?,
     val title: String?,
     val description: String?,
     val url: String?,
     val urlToImage: String?
) : Parcelable

