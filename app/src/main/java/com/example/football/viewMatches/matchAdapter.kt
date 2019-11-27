package com.example.football.viewMatches

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.football.R
import com.example.football.data.matchData.MatchList
import kotlinx.android.synthetic.main.grid_layout.view.*
import kotlinx.android.synthetic.main.activity_viewmatches.view.*
import kotlinx.android.synthetic.main.row_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


/*
    The Match Adapter is part of the RecycleViewer

    It dynamically binds views to the screen, views that are not on the screen get cleared
    This is also the place where we can bind date to the view

    @Constructor creates a matchAdapter with a list of matches and some context
    @property matchList is a list of matches retrieved from an API

 */

class MatchAdapter(val matchList: MatchList?, val context: Context): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.row_layout, parent, false)
        )
    }



    var parsedName: String? = null
    var isHeader: Boolean = false

    override fun getItemCount(): Int {

        return matchList!!.matches.size

    }


    /*
        This is the function where views get bound to the ViewHolder
        The ViewHolder are the views currently loaded
        When scrolling a new view gets bound

     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //get current match
        val currentMatch = matchList!!.matches[position]


        //get view matchDay
        val viewMatchDay = currentMatch.matchday


        //convert UTC time to local time
        val utcDate = currentMatch.utcDate.toString()
        holder.viewTime?.text = getLocalTime(utcDate)



        // get currentView date and previousView date
        val matchDate = utcDate.substring(0, 10)
        var previousDate = matchList.matches[position-1].utcDate.toString()
        previousDate = previousDate.substring(0, 10)


        // check if date and matchday should be displayed, we only want to display them if the date changes
        if (matchDate != previousDate) {


            val dayString = "Matchday $viewMatchDay"
            currentMatch.viewType = "header"


            holder.viewMatchDay.text = dayString
            holder.viewMatchDay.visibility = View.VISIBLE


            holder.viewDate.text = matchDate
            holder.viewDate.visibility = View.VISIBLE

        } else {
            holder.viewDate.visibility = View.GONE
            holder.viewMatchDay.visibility = View.GONE

        }

        // get the team names as described in the API
        val home = currentMatch.homeTeam?.name
        val away = currentMatch.awayTeam?.name


        /*
            The API team names are kinda long, could be troublesome for an app
            Team names are parsed to make the app more readable, and team logo's are added for clarity

            First check is which competition parser should be used. The API specifies competition codes.
            Team names and logos are instantiated according to their parsers

         */
        when(matchList.competition!!.code){
            "DED" -> {
                holder.viewLogo1.setImageResource(parseEreTeams(home.toString()))
                val homeString = "$parsedName vs. "
                holder.viewTeam1.text = homeString
                holder.viewLogo2.setImageResource(parseEreTeams(away.toString()))
                holder.viewTeam2.text = parsedName
            }
            "PL" -> {
                holder.viewLogo1.setImageResource(parsePLTeams(home.toString()))
                val homeString = "$parsedName vs. "
                holder.viewTeam1.text = homeString
                holder.viewLogo2.setImageResource(parsePLTeams(away.toString()))
                holder.viewTeam2.text = parsedName
            }
        }


        // get fulltime match score
        val matchScore = currentMatch.score?.fullTime
        val score = matchScore?.homeTeam.toString().first() + " - " + matchScore?.awayTeam.toString().first()


        /*
            How should the score be displayed? What is the match status?
            Possible statuses: FINISHED, SCHEDULED, LIVE, IN-PLAY etc
         */
        handleStatus(currentMatch.status, holder, score)


        Log.d("Chris", currentMatch.viewType.toString())
        Log.d("Chris", getItemViewType(position).toString())

    }


    override fun getItemViewType(pos: Int): Int{
        if(matchList!!.matches[pos].viewType == "header"){
            return 0
        } else{
            return 1
        }
    }


    // Converts a given utcDate time to the devices local time
    fun getLocalTime(utcDate: String): String{
        val dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("UTC")

        val date: Date? = parser.parse(utcDate)
        return date.toString().substring(11, 16)

    }

    // Parses eredivisie teamnames and assigns logos
    fun parseEreTeams(team: String): Int{
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

    // Parses premier league teamnames and assigns logos
    fun parsePLTeams(team: String): Int {
        when (team) {
            "Norwich City FC" -> {
                parsedName = "Norwich City"
                return R.drawable.norwich
            }
            "Liverpool FC" -> {
                parsedName = "Liverpool"
                return R.drawable.liverpool
            }
            "Manchester City FC" -> {
                parsedName = "Manchester City"
                return R.drawable.mancity
            }
            "West Ham United FC" -> {
                parsedName = "WestHam United"
                return R.drawable.westham
            }
            "Leicester City FC" -> {
                parsedName = "Leicester"
                return R.drawable.leicester
            }
            "Southampton FC" -> {
                parsedName = "Southampton"
                return R.drawable.southampton
            }
            "Burnley FC" -> {
                parsedName = "Burnley"
                return R.drawable.burnley
            }
            "Everton FC" -> {
                parsedName = "Everton"
                return R.drawable.everton
            }
            "Crystal Palace FC" -> {
                parsedName = "Crystal Palace"
                return R.drawable.crystal
            }
            "Brighton & Hove Albion FC" -> {
                parsedName = "Brighton & HA"
                return R.drawable.brighton
            }
            "Watford FC" -> {
                parsedName = "Watford"
                return R.drawable.watford
            }
            "Chelsea FC" -> {
                parsedName = "Chelsea"
                return R.drawable.chelsea
            }
            "Wolverhampton Wanderers FC" -> {
                parsedName = "Wolverhampton"
                return R.drawable.wolverhampton
            }
            "Sheffield United FC" -> {
                parsedName = "Sheffield United"
                return R.drawable.sheffield
            }
            "Arsenal FC" -> {
                parsedName = "Arsenal"
                return R.drawable.arsenal
            }
            "Manchester United FC" -> {
                parsedName = "Manchester United"
                return R.drawable.manchester
            }
            "Tottenham Hotspur FC" -> {
                parsedName = "Tottenham Hotspur"
                return R.drawable.tottenham
            }
            "AFC Bournemouth" -> {
                parsedName = "Bournemouth"
                return R.drawable.bournemouth
            }
            "Newcastle United FC" -> {
                parsedName = "Newcastle United"
                return R.drawable.newcastle
            }
            "Aston Villa FC" -> {
                parsedName = "Aston Villa"
                return R.drawable.aston
            }
            else -> return R.drawable.ic_launcher_background
        }
    }

    // Handles match status and acts accordingly
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

// assigns views field ID's to variables
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
    var recyc = view.match_list
}

