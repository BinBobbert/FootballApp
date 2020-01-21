package com.example.football.ui.home

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.R
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.network.response.CompData
import com.example.football.data.repository.MatchesRepository
import com.example.football.internal.lazyDeferred
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel (
    private val matchesRepository: MatchesRepository
): ViewModel(){
    private val _currentMatches = MutableLiveData<List<Matche>>()
    private var compCode = "DED"

    val currentMatches: LiveData<List<Matche>>
        get() = _currentMatches

    init {
        viewModelScope.launch {
            val matches = matchesRepository.getMatches(compCode)
            _currentMatches.postValue(matches)
        }

    }

    fun changeComp(code: String){
        compCode = code

        viewModelScope.launch {
            val matches = matchesRepository.getMatches(compCode)
            _currentMatches.postValue(matches)
        }
    }

    fun getCompCode(): String{
        return compCode
    }


}

