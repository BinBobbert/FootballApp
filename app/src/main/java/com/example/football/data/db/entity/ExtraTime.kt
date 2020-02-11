package com.example.football.data.matchData.gsonData


import com.google.gson.annotations.SerializedName

data class ExtraTime(
    @SerializedName("awayTeam")
    var awayTeam: Int?,
    @SerializedName("homeTeam")
    var homeTeam: Int?
)