package com.example.football.data.network.response.tryout


import com.google.gson.annotations.SerializedName

data class NetworkResponse(
    @SerializedName("competition")
    val competition: Competition?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("filters")
    val filters: Filters?,
    @SerializedName("matches")
    val matches: List<Matche?>?
)