package com.example.namegame.eredivisie

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.namegame.R
import com.example.namegame.data.matchData.MatchList
import kotlinx.android.synthetic.main.row_layout.view.*

class MatchAdapter(val matchList: MatchList?, val context: Context): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false))
    }

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
        val utcDate = currentMatch.utcDate.toString()
        val matchDate = utcDate.substring(0, 10)
        val matchTime = utcDate.substring(11, 16)

        val display = home.toString() + " vs. " + away.toString() + "  " + matchTime + "\n" + matchDate


        holder.matchName?.text = display
    }


}


class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    var matchName = view.match_name



}
