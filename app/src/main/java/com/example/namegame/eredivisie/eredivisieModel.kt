package com.example.namegame.eredivisie

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.namegame.R
import com.example.namegame.data.matchData.MatchList
import kotlinx.android.synthetic.main.row.view.*
import kotlinx.android.synthetic.main.row_layout.view.*
import java.time.LocalDate

class MatchAdapter(val matchList: MatchList?, val context: Context): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false))
    }

    var startingPos: Int = 0

    override fun getItemCount(): Int {
        Log.d("Chris", "THIS IS THE MATCH COUNT: " + matchList?.count.toString())

        if (matchList?.count == null){
            return 0
        } else {
            return matchList.count
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMatch = matchList!!.matches[position]

        val home = currentMatch.homeTeam?.name
        val away = currentMatch.awayTeam?.name
        val matchName = home.toString() + " vs. " + away.toString()
        val utcDate = currentMatch.utcDate.toString()
        val matchDate = utcDate.substring(0, 10)
        val matchTime = utcDate.substring(11, 16)


        Log.d("Dates", matchDate + " compare to " + LocalDate.now().toString())


        holder.viewName?.text = matchName
        holder.viewDate?.text = matchDate
        holder.viewTime?.text = matchTime

    }

    fun getStartPos(): Int{
        return startingPos
    }




}


class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    var viewName = view.match_teams
    var viewTime = view.match_time
    var viewDate = view.match_date



}
