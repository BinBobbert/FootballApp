package com.example.football.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.football.data.repository.MatchesRepository

class HomeViewModelFactory(
    private val matchesRepository: MatchesRepository
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(matchesRepository) as T
    }



}