package com.example.football.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.repository.MatchesRepository
import kotlinx.coroutines.launch

class HomeViewModel (
    private val matchesRepository: MatchesRepository
): ViewModel(){
    private val _currentMatches = MutableLiveData<List<Matche>>()

    val currentMatches: LiveData<List<Matche>>
        get() = _currentMatches


    init {
        viewModelScope.launch {
            val matches = matchesRepository.getAllMatches()
            _currentMatches.postValue(matches)
        }

    }



}

