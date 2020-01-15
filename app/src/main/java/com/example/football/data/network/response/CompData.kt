package com.example.football.data.network.response


import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.network.response.tryout.Competition
import com.example.football.data.network.response.tryout.Filters
import com.google.gson.annotations.SerializedName

data class CompData(
    @SerializedName("competition")
    val competition: Competition?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("filters")
    val filters: Filters?,
    @SerializedName("matches")
    var matches: List<Matche>
)