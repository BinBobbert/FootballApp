package com.example.football.data.repository

import androidx.lifecycle.LiveData
import com.example.football.data.matchData.gsonData.Matche

interface MatchesRepository {
    suspend fun getMatches(compCode: String) : List<Matche>

    suspend fun getAllMatches(): List<Matche>

    suspend fun getSpecificCompMatches(compCodes: List<String>) : List<Matche>
}