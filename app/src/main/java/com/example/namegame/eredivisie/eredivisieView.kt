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


class EredivisieView (private val api: FootballApi, val presenter: EredivisiePresenter): ViewModel(){





    fun fetchMatches(date: String) {

        viewModelScope.launch(Dispatchers.Main){

            try {

                val matchList = api.getAllEre()
                //val matchList = api.getEreMatches(date, "2020-05-20")
                presenter.updateRecyc(matchList)

                Log.d("Chris", "goddammit why no work")
                Log.d("CHRIS", matchList.toString())






            } catch(e: Exception){
                Log.d("Error", e.toString())
            }


        }



    }



}