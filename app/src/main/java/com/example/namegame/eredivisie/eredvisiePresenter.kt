package com.example.namegame.eredivisie

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namegame.NothaWay.ApiFactory.footballAPI
import com.example.namegame.R
import com.example.namegame.data.matchData.MatchList
import com.example.namegame.data.matchData.Matches
import kotlinx.android.synthetic.main.activity_eredivisie.*
import java.time.LocalDate

class EredivisiePresenter : AppCompatActivity() {

    var matchList: MatchList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eredivisie)

        val eView = EredivisieView(footballAPI, this)
        val dateToday = LocalDate.now()


        eView.fetchMatches(dateToday)


        Log.d("Chris", "When does this happen?")





    }



    fun updateRecyc(matchList: MatchList){

        this.matchList = matchList

        val manager = LinearLayoutManager(this)

        match_list.layoutManager = manager

        val matchAdapter = MatchAdapter(matchList, this)

        match_list.adapter = matchAdapter


        Log.d("Chris", match_list.adapter!!.itemCount.toString())
        manager.scrollToPosition(match_list.adapter!!.itemCount-1)





    }

    fun addMatches(matchList: MatchList){
        //current matches
        //var currentMatchlist: MutableList<Matches> = this.matchList!!.matches

        //matches to come
        val matchesToBe: MutableList<Matches> = matchList.matches

        //get the index of where we need to add the upcoming matches
        val index = match_list.adapter!!.itemCount

        //add the upcoming matches to the matchList used by our adapter
        this.matchList!!.matches.addAll(index, matchesToBe)


        //Notify the adapter
        match_list.adapter!!.notifyItemRangeInserted(index, match_list.adapter!!.itemCount)




    }




}
