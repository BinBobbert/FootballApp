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
import kotlinx.android.synthetic.main.row_layout.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MatchAdapter(val matchList: MatchList?, val context: Context): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false))
    }

    var parsedName: String? = null
    var firstMatchDate = true
    var currentMatchDate: String? = null

    override fun getItemCount(): Int {

        return matchList!!.matches.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMatch = matchList!!.matches[position]

        //should we display the matchday

        //get view matchDay
        val viewMatchDay = currentMatch.matchday
        Log.d("matchday", currentMatch.matchday.toString())






        //convert UTC time to local time
        val utcDate = currentMatch.utcDate.toString()
        val matchDate = utcDate.substring(0, 10)
        holder.viewTime?.text = getLocalTime(utcDate)
        holder.viewDate?.text = matchDate


        if (firstMatchDate){
            currentMatchDate = matchDate
            firstMatchDate = false
        }

        var previousDate = matchList.matches[position-1].utcDate.toString()
        previousDate = previousDate.substring(0, 10)



        Log.d("Date", "Previous Date: $previousDate" )
        Log.d("Date", "Current Date: $matchDate" )


        if (matchDate != previousDate) {
            val dayString = "Matchday $viewMatchDay"
            holder.viewMatchDay.text = dayString
            holder.viewMatchDay.visibility = View.VISIBLE

            holder.viewDate.text = matchDate
            holder.viewDate.visibility = View.VISIBLE
            currentMatchDate = matchDate
        } else {
            holder.viewDate.visibility = View.GONE

        }


        val home = currentMatch.homeTeam?.name
        val away = currentMatch.awayTeam?.name
        //Find logos for the corresponding teams
        holder.viewLogo1.setImageResource(getTeamLogo(home.toString()))
        val homeString = "$parsedName  vs.  "
        holder.viewTeam1.text = homeString


        holder.viewLogo2.setImageResource(getTeamLogo(away.toString()))
        holder.viewTeam2.text = parsedName


        val matchScore = currentMatch.score?.fullTime

        // TODO: Make a handelStatus fun
        val score = matchScore?.homeTeam.toString().first() + " - " + matchScore?.awayTeam.toString().first()
        handleStatus(currentMatch.status, holder, score)


    }

    fun getLocalTime(utcDate: String): String{
        val dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("UTC")

        val date: Date? = parser.parse(utcDate)
        return date.toString().substring(11, 16)

    }

    fun getTeamLogo(team: String): Int{
        when (team){
            "AFC Ajax" -> {
                parsedName= "Ajax"
                return R.drawable.ajax
            }
            "ADO Den Haag" ->{
                parsedName= "ADO"
                return R.drawable.ado
            }
            "FC Groningen" -> {
                parsedName= "Groningen"
                return R.drawable.groningen
            }
            "Feyenoord Rotterdam" -> {
                parsedName= "Feyenoord"
                return R.drawable.feyenoord
            }
            "Sparta Rotterdam" -> {
                parsedName= "Sparta"
                return R.drawable.sparta
            }
            "FC Emmen" -> {
                parsedName= "Emmen"
                return R.drawable.emmen
            }
            "Fortuna Sittard" -> {
                parsedName= "Fortuna"
                return R.drawable.fortuna
            }
            "VVV Venlo" -> {
                parsedName= "Venlo"
                return R.drawable.venlo
            }
            "Willem II Tilburg" -> {
                parsedName= "Willem II"
                return R.drawable.willem
            }
            "SBV Vitesse" -> {
                parsedName= "Vitesse"
                return R.drawable.vitesse
            }
            "FC Twente '65" -> {
                parsedName= "Twente"
                return R.drawable.twente
            }
            "PSV" -> {
                parsedName= "PSV"
                return R.drawable.psv
            }
            "RKC Waalwijk" -> {
                parsedName= "RKC"
                return R.drawable.waalwijk
            }
            "Heracles Almelo" -> {
                parsedName= "Heracles"
                return R.drawable.heracles
            }
            "SC Heerenveen" -> {
                parsedName= "Heerenveen"
                return R.drawable.heerenveen
            }
            "FC Utrecht" -> {
                parsedName= "Utrecht"
                return R.drawable.utrecht
            }
            "PEC Zwolle" -> {
                parsedName= "Zwolle"
                return R.drawable.zwolle
            }
            "AZ" -> {
                parsedName= "AZ"
                return R.drawable.az
            }
            else -> return R.drawable.ic_launcher_background
        }
    }

    fun handleStatus(status: String?, holder: ViewHolder, score:String){
        Log.d("Status", status.toString())
        Log.d("Status", score)

        when(status){
            "FINISHED" -> {
                holder.viewScore.visibility = View.VISIBLE
                holder.viewPlay.visibility = View.INVISIBLE
                holder.viewScore?.text =  score
            }
            "PAUSED", "IN_PLAY", "LIVE" -> {
                holder.viewScore.visibility = View.VISIBLE
                holder.viewPlay.visibility = View.VISIBLE
                holder.viewScore?.text =  score
            }


            else -> {
                holder.viewScore.visibility = View.INVISIBLE
                holder.viewPlay.visibility = View.INVISIBLE
            }

        }
    }





}


class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    var viewTeam1 = view.match_team
    var viewTeam2 = view.match_team2
    var viewLogo1 = view.home_logo
    var viewLogo2 = view.away_logo
    var viewTime = view.match_time
    var viewDate = view.match_date
    var viewScore = view.match_score
    var viewPlay = view.in_play
    var viewMatchDay = view.matchday
}

