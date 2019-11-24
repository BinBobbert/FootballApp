package com.example.namegame.eredivisie


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namegame.NothaWay.FootballApi
import com.example.namegame.data.matchData.MatchList
import kotlinx.android.synthetic.main.activity_eredivisie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.time.LocalDate


class EredivisieView (private val api: FootballApi, val presenter: EredivisiePresenter): ViewModel(){





    fun fetchMatches(date: LocalDate) {

        viewModelScope.launch(Dispatchers.Main){

            try {

                Log.d("Date", date.toString())

                var matchList = api.getEreMatches("2019-08-02", date.toString())

                presenter.updateRecyc(matchList)


                Log.d("Date", date.plusDays(1).toString())

                matchList = api.getEreMatches(date.plusDays(1).toString(), "2020-05-20")



                presenter.addMatches(matchList)






            } catch(e: Exception){
                Log.d("Error", e.toString())
            }

        }



    }



}