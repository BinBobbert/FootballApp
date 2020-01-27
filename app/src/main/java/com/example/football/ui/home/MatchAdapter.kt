
package com.example.football.ui.home

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.football.R
import com.example.football.data.network.response.CompData
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.databinding.MatchThemeBinding
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MatchAdapter(val matchList: List<Matche>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    private var currentComp: String = ""

    inner class MatchViewHolder(val binding: MatchThemeBinding) : RecyclerView.ViewHolder(binding.root) {

        val header = binding.matchDate

        fun isHeader(pos: Int): Boolean{
            if(pos == 0){
                return true
            } else {
                if(matchList[pos].utcDate!!.substring(8,10) != matchList[pos-1].utcDate!!.substring(8,10)){
                    return true
                }
                return false
            }
        }

        fun showComp(pos: Int): Boolean{
            if(pos == 0){
                when(matchList[pos].compCode){
                    "PL" -> currentComp = "Premier League"
                    "DED" -> currentComp = "Eredivisie"
                    "SA" -> currentComp = "Serie A"
                }
                return true
            } else if(isHeader(pos)){
                when(matchList[pos].compCode){
                    "PL" -> currentComp = "Premier League"
                    "DED" -> currentComp = "Eredivisie"
                    "SA" -> currentComp = "Serie A"
                }
                return true
            } else if(matchList[pos].compCode != matchList[pos-1].compCode){
                    when(matchList[pos].compCode){
                        "PL" -> currentComp = "Premier League"
                        "DED" -> currentComp = "Eredivisie"
                        "SA" -> currentComp = "Serie A"
                    }
                    return true
                }
                return false
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
        MatchViewHolder(
            DataBindingUtil.inflate<MatchThemeBinding>(
                LayoutInflater.from(parent.context),
                R.layout.match_theme,
                parent,
                false
            )


        )

    fun getCurrentMatchday(): Int{
        val zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedString = zonedDateTime.format(formatter)

        val match = matchList.binarySearch {
            String.CASE_INSENSITIVE_ORDER.compare(it.date, formattedString)
        }

        return match * -1
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        holder.binding.matchTheme = matchList[position]

        if (holder.showComp(position)) {

            holder.binding.competitionName.text = currentComp
            holder.binding.competitionName.visibility = View.VISIBLE
            holder.binding.matchDay.visibility = View.VISIBLE
        } else {
            holder.binding.competitionName.visibility = View.GONE
            holder.binding.matchDay.visibility = View.GONE
        }

        if(holder.isHeader(position)){
            holder.header.visibility = View.VISIBLE
        } else {
            holder.header.visibility = View.GONE
        }

    }

}



