package com.example.football.data.matchData.gsonData


import com.google.gson.annotations.SerializedName

data class FullTime(
    @SerializedName("awayTeam")
    var awayTeamScore: Int?,
    @SerializedName("homeTeam")
    var homeTeamScore: Int?
)