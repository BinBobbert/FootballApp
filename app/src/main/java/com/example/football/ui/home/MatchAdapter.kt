
package com.example.football.ui.home

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
import java.util.*

class MatchAdapter(val matchList: List<Matche>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: MatchThemeBinding) : RecyclerView.ViewHolder(binding.root) {

        val header = binding.group

        fun isHeader(pos: Int): Boolean{

            if(matchList[pos].utcDate!!.substring(8,10) != matchList[pos-1].utcDate!!.substring(8,10)){
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


    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        holder.binding.matchTheme = matchList[position]

        if(holder.isHeader(position)){
            holder.header.visibility = View.VISIBLE
        } else {
            holder.header.visibility = View.GONE
        }

    }




}



