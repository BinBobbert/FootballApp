package com.example.football.viewMatches


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.Retrofit.FootballApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate


/*
    The ViewModel handles our API requests and updates the presenter with data

    @params FootballApi: Retrofit API object, used to send API requests
    @params MatchPresenter: Initializes and updates the recycleViewer

 */
class MatchViewModel (private val api: FootballApi, private val presenter: MatchPresenter): ViewModel(){


    // Fetch all matches from a competition
    fun fetchMatches(date: LocalDate) {

        // start coroutine
        viewModelScope.launch(Dispatchers.Main){

            try {


                // Get matches up to the currentDate, and update the recycler
                var matchList = api.getMatches(presenter.comp,"2019-08-02", date.toString())
                presenter.updateRecyc(matchList)

                // Get matches up from currentDate, add the matches to the adapter and notify it of change.
                matchList = api.getMatches(presenter.comp, date.plusDays(1).toString(), "2020-05-20")
                presenter.addMatches(matchList)


            } catch(e: Exception){
                Log.d("Error", e.toString())
            }

        }

    }
}