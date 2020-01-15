package com.example.football.data.matchData.gsonData


import androidx.room.Embedded
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

data class Score(
    @Embedded(prefix = "fullTime_")
    var fullTime: FullTime?
)