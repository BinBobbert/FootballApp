package com.example.football.data.matchData.gsonData


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "matches")
data class Matche(
    @Embedded(prefix = "awayTeam_")
    var awayTeam: AwayTeam?,
    @Embedded(prefix = "homeTeam_")
    var homeTeam: HomeTeam?,
    @SerializedName("matchday")
    var dayOfMatch: Int?,
    @Embedded(prefix = "score_")
    var score: Score?,
    @Embedded(prefix = "season_")
    var season: Season?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("utcDate")
    var utcDate: String?,
    var compCode: String?
) {
    @PrimaryKey(autoGenerate = true)
    var keyz: Int = 1
}
