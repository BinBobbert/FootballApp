package com.example.football.data.matchData.gsonData


import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

data class AwayTeam(
    @SerializedName("name")
    var name: String?
)