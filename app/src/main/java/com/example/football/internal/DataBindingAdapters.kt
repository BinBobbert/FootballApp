package com.example.football.internal

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.football.R
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, resource: Int){
    imageView.setImageResource(resource)
}

fun getLocalTime(utcDate: String): String{

    val dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = TimeZone.getTimeZone("UTC")

    val date: Date? = parser.parse(utcDate)
    return date.toString().substring(11, 16)
}

fun getDateFormat(utcDate: String): String{

    when(utcDate.substring(5,7)){
        "01" -> return "Januari ${utcDate.substring(8, 10)}"
        "02" -> return "Februari ${utcDate.substring(8, 10)}"
        "03" -> return "March ${utcDate.substring(8, 10)}"
        "04" -> return "April ${utcDate.substring(8, 10)}"
        "05" -> return "Mei ${utcDate.substring(8, 10)}"
        "06" -> return "June ${utcDate.substring(8, 10)}"
        "07" -> return "Juli ${utcDate.substring(8, 10)}"
        "08" -> return "August ${utcDate.substring(8, 10)}"
        "09" -> return "September ${utcDate.substring(8, 10)}"
        "10" -> return "Oktober ${utcDate.substring(8, 10)}"
        "11" -> return "November ${utcDate.substring(8, 10)}"
        "12" -> return "December ${utcDate.substring(8, 10)}"
        else -> return "null"
    }
}

fun parseComp(code: String): String{
    when(code){
        "PL" -> return "Premier League"
        "DED" -> return "Eredivisie"
        "SA" -> return "Serie A"
        else -> return "Competition Name"
    }
}

fun parseEreTeams(team: String): Int{
    when (team){
        "AFC Ajax" -> {
            return R.drawable.ajax
        }
        "ADO Den Haag" ->{
            return R.drawable.ado
        }
        "FC Groningen" -> {
            return R.drawable.groningen
        }
        "Feyenoord Rotterdam" -> {
            return R.drawable.feyenoord
        }
        "Sparta Rotterdam" -> {
            return R.drawable.sparta
        }
        "FC Emmen" -> {
            return R.drawable.emmen
        }
        "Fortuna Sittard" -> {
            return R.drawable.fortuna
        }
        "VVV Venlo" -> {
            return R.drawable.venlo
        }
        "Willem II Tilburg" -> {
            return R.drawable.willem
        }
        "SBV Vitesse" -> {
            return R.drawable.vitesse
        }
        "FC Twente '65" -> {
            return R.drawable.twente
        }
        "PSV" -> {
            return R.drawable.psv
        }
        "RKC Waalwijk" -> {
            return R.drawable.waalwijk
        }
        "Heracles Almelo" -> {
            return R.drawable.heracles
        }
        "SC Heerenveen" -> {
            return R.drawable.heerenveen
        }
        "FC Utrecht" -> {
            return R.drawable.utrecht
        }
        "PEC Zwolle" -> {
            return R.drawable.zwolle
        }
        "AZ" -> {
            return R.drawable.az
        }
        "Norwich City FC" -> {
            return R.drawable.norwich
        }
        "Liverpool FC" -> {
            return R.drawable.liverpool
        }
        "Manchester City FC" -> {
            return R.drawable.mancity
        }
        "West Ham United FC" -> {
            return R.drawable.westham
        }
        "Leicester City FC" -> {
            return R.drawable.leicester
        }
        "Southampton FC" -> {
            return R.drawable.southampton
        }
        "Burnley FC" -> {
            return R.drawable.burnley
        }
        "Everton FC" -> {
            return R.drawable.everton
        }
        "Crystal Palace FC" -> {
            return R.drawable.crystal
        }
        "Brighton & Hove Albion FC" -> {
            return R.drawable.brighton
        }
        "Watford FC" -> {
            return R.drawable.watford
        }
        "Chelsea FC" -> {
            return R.drawable.chelsea
        }
        "Wolverhampton Wanderers FC" -> {
            return R.drawable.wolverhampton
        }
        "Sheffield United FC" -> {
            return R.drawable.sheffield
        }
        "Arsenal FC" -> {
            return R.drawable.arsenal
        }
        "Manchester United FC" -> {
            return R.drawable.manchester
        }
        "Tottenham Hotspur FC" -> {
            return R.drawable.tottenham
        }
        "AFC Bournemouth" -> {
            return R.drawable.bournemouth
        }
        "Newcastle United FC" -> {
            return R.drawable.newcastle
        }
        "Aston Villa FC" -> {
            return R.drawable.aston
        }
        else -> return R.drawable.ic_launcher_background
    }
}

fun parsePLTeams(team: String): Int {
    when (team) {
        "Norwich City FC" -> {
            return R.drawable.norwich
        }
        "Liverpool FC" -> {
            return R.drawable.liverpool
        }
        "Manchester City FC" -> {
            return R.drawable.mancity
        }
        "West Ham United FC" -> {
            return R.drawable.westham
        }
        "Leicester City FC" -> {
            return R.drawable.leicester
        }
        "Southampton FC" -> {
            return R.drawable.southampton
        }
        "Burnley FC" -> {
            return R.drawable.burnley
        }
        "Everton FC" -> {
            return R.drawable.everton
        }
        "Crystal Palace FC" -> {
            return R.drawable.crystal
        }
        "Brighton & Hove Albion FC" -> {
            return R.drawable.brighton
        }
        "Watford FC" -> {
            return R.drawable.watford
        }
        "Chelsea FC" -> {
            return R.drawable.chelsea
        }
        "Wolverhampton Wanderers FC" -> {
            return R.drawable.wolverhampton
        }
        "Sheffield United FC" -> {
            return R.drawable.sheffield
        }
        "Arsenal FC" -> {
            return R.drawable.arsenal
        }
        "Manchester United FC" -> {
            return R.drawable.manchester
        }
        "Tottenham Hotspur FC" -> {
            return R.drawable.tottenham
        }
        "AFC Bournemouth" -> {
            return R.drawable.bournemouth
        }
        "Newcastle United FC" -> {
            return R.drawable.newcastle
        }
        "Aston Villa FC" -> {
            return R.drawable.aston
        }
        else -> return R.drawable.ic_launcher_background
    }
}
