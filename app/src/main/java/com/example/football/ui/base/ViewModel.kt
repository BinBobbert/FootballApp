package com.example.football.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.repository.MatchesRepository

interface ViewModel{
    val matchesRepository: MatchesRepository

    val _currentMatches: MutableLiveData<List<Matche>>
    val currentMatches: LiveData<List<Matche>>

}