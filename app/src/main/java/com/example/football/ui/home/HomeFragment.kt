package com.example.football.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football.R
import com.example.football.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(


        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("chris", "OnCreateView HomeFragment")
        homeViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        bindUI()
    }

    private fun bindUI() = launch{
        val matchList = homeViewModel.currentMatches
        //test.text = matchList.value.toString()


        matchList.observe(this@HomeFragment, Observer{
            if (it == null) return@Observer

            matches_recycle.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = MatchAdapter(matchList.value!!)

            }

            matches_recycle.layoutManager!!.scrollToPosition( (matchList.value!![0].season!!.currentMatchday!! * 9) - 1)
            home_progressbar.visibility = View.INVISIBLE
            matches_recycle.visibility = View.VISIBLE
        })

    }
}