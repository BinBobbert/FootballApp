package com.example.football.data.network

import androidx.lifecycle.LiveData
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.network.response.CompData
import com.example.football.data.network.response.tryout.NetworkResponse

interface FootballNetworkDataSource {
    val downloadedMatches : LiveData<CompData>

    suspend fun fetchMatches(
        code: String
    )
}