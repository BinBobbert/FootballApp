package com.example.namegame.data.matchData


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Competition(
    @Json(name = "area")
    val area: Area?,
    @Json(name = "code")
    val code: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "lastUpdated")
    val lastUpdated: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "plan")
    val plan: String?
)