package com.example.namegame.data.matchData


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AwayTeam(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)