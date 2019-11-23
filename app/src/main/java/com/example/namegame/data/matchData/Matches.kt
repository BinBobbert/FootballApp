package com.example.namegame.data.matchData


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Matches(
    @Json(name = "awayTeam")
    val awayTeam: AwayTeam?,
    @Json(name = "group")
    val group: String?,
    @Json(name = "homeTeam")
    val homeTeam: HomeTeam?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "lastUpdated")
    val lastUpdated: String?,
    @Json(name = "matchday")
    val matchday: Int?,
    @Json(name = "referees")
    val referees: List<Any?>?,
    @Json(name = "score")
    val score: Score?,
    @Json(name = "season")
    val season: Season?,
    @Json(name = "stage")
    val stage: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "utcDate")
    val utcDate: String?
)