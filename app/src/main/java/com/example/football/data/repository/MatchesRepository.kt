package com.example.football.data.repository

import androidx.lifecycle.LiveData
import com.example.football.data.matchData.gsonData.Matche

interface MatchesRepository {
    suspend fun getMatches() : List<Matche>
}