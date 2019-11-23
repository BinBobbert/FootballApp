package com.example.namegame.data.matchData


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Penalties(
    @Json(name = "awayTeam")
    val awayTeam: Any?,
    @Json(name = "homeTeam")
    val homeTeam: Any?
)