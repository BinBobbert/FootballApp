package com.example.football.data.matchData.gsonData


import com.google.gson.annotations.SerializedName

data class Penalties(
    @SerializedName("awayTeam")
    var awayTeam: String?,
    @SerializedName("homeTeam")
    var homeTeam: String?
)