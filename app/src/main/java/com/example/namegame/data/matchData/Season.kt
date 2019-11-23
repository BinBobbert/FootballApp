package com.example.namegame.data.matchData


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name = "currentMatchday")
    val currentMatchday: Int?,
    @Json(name = "endDate")
    val endDate: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "startDate")
    val startDate: String?
)