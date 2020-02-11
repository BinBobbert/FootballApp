package com.example.football.data.network.response.tryout


import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)