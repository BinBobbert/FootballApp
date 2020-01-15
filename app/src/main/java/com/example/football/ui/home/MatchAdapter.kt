
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

class MatchAdapter(val matchList: List<Matche>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: MatchThemeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(match: Matche){
            binding.matchTheme = match
            binding.executePendingBindings()
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

    // only works for eredivisie right now
    fun scrollToCurrent(): Int{
        val currentMatchDay: Int = matchList[0].season!!.currentMatchday!!

        return  currentMatchDay * 9
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        holder.binding.matchTheme = matchList[position]

    }


}



