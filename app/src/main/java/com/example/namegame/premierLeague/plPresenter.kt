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

class PLPresenter : AppCompatActivity() {

    //var matchList: MatchList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eredivisie)

        val eView = PLView(footballAPI, this)
        val dateToday = LocalDate.now().toString()

        Log.d("Date", dateToday)


        eView.fetchMatches(dateToday)


        Log.d("Chris", "When does this happen?")





    }



    fun updateRecyc(matchList: MatchList){
        match_list.layoutManager = LinearLayoutManager(this)
        match_list.adapter = MatchAdapter(matchList, this)
    }


}
