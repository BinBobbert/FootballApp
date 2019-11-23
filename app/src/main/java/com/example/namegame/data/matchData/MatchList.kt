package com.example.namegame.data.matchData


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchList(
    @Json(name = "competition")
    val competition: Competition?,
    @Json(name = "count")
    val count: Int,
    @Json(name = "filters")
    val filters: Filters?,
    @Json(name = "matches")
    val matches: List<Matches>
)