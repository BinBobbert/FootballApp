package com.example.football.viewMatches

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football.R
import com.example.football.Retrofit.FootballApi.Companion.footballAPI
import com.example.football.data.matchData.MatchList
import com.example.football.data.matchData.Matches
import kotlinx.android.synthetic.main.activity_viewmatches.*
import kotlinx.android.synthetic.main.content_main.*
import java.time.LocalDate

/*
    The MatchPresenter initializes the RecycleViewer by specifying the layout and adapter
    Uses the viewmodel to get data
    The viewmodel then updates the presenter/recyclerview
 */
class MatchPresenter : AppCompatActivity() {

    var matchList: MatchList? = null
    var comp: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmatches)

        // init ViewModel with the Retrofit API
        val eView = MatchViewModel(footballAPI, this)

        // Date needed to so we know where to scroll to
        val dateToday = LocalDate.now()

        // competition is needed to specify which competition we want to load
        comp = intent.getStringExtra("comp")


        eView.fetchMatches(dateToday)


        /*
        switch1.setOnClickListener {
            match_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            LayoutInflater.from(this).inflate(R.layout.row_layout, match_list, false)


        }
        */

    }



    /*
        Updates the recycleviewer with matches from the start of the season until currentDate
        RecycleViewer layoutManager and adapter are specified.
        We scroll to the end of the list (current date)

     */
    fun updateRecyc(matchList: MatchList){

        this.matchList = matchList

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        /*
        manager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                val mAdap = match_list.adapter
                when(mAdap!!.getItemViewType(position)){
                    0 -> 2
                    else ->  1

                }
            }
        }

         */


        match_list.layoutManager = manager

        val matchAdapter =
            MatchAdapter(matchList, this)

        match_list.adapter = matchAdapter



        manager.scrollToPosition(match_list.adapter!!.itemCount-1)

    }



    /*
        Adds the matches from currentDate until the end of the season to the adapters matchList
        Notify the adapter that the data set has changed, otherwise the change will not be displayed
        Recycleviewer should now show all matches for a specified competition
     */
    fun addMatches(matchList: MatchList){

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
