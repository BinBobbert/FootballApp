package com.example.football.data.matchData.gsonData


import com.google.gson.annotations.SerializedName

data class HalfTime(
    @SerializedName("awayTeam")
    var awayTeam: Int?,
    @SerializedName("homeTeam")
    var homeTeam: Int?
)