package com.example.namegame.eredivisie

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namegame.NothaWay.ApiFactory.footballAPI
import com.example.namegame.R
import com.example.namegame.data.matchData.MatchList
import kotlinx.android.synthetic.main.activity_eredivisie.*
import java.time.LocalDate

class EredivisiePresenter : AppCompatActivity() {

    //var matchList: MatchList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eredivisie)

        val eView = EredivisieView(footballAPI, this)
        val dateToday = LocalDate.now().toString()

        Log.d("Date", dateToday)


        eView.fetchMatches(dateToday)


        Log.d("Chris", "When does this happen?")





    }



    fun updateRecyc(matchList: MatchList){

        val manager = LinearLayoutManager(this)

        match_list.layoutManager = manager

        val matchAdapter = MatchAdapter(matchList, this)
        matchList.matches

        match_list.adapter = matchAdapter


        manager.scrollToPosition(15)
    }


    fun findMatchDate(matchList: MatchList){
        val matchIte = matchList
    }



}
