package com.example.football.data.matchData.gsonData


import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

data class HomeTeam(
    @SerializedName("name")
    var name: String?
)