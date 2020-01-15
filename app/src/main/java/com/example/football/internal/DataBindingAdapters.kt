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
        else -> return R.drawable.ic_launcher_background
    }
}
