package com.example.football.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
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
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


class HomeFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(


        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

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

            var scrollTo = 0
            when(homeViewModel.getCompCode()){
                "DED" -> scrollTo = (matchList.value!![0].season!!.currentMatchday!! * 9)
                "PL" -> scrollTo = (matchList.value!![0].season!!.currentMatchday!! * 10)
            }

            matches_recycle.layoutManager!!.scrollToPosition(scrollTo)
            home_progressbar.visibility = View.INVISIBLE
            matches_recycle.visibility = View.VISIBLE
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.comp_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.comp_ere -> {
                homeViewModel.changeComp("DED")
                bindUI()
                return true
            }
            R.id.comp_pl -> {
                homeViewModel.changeComp("PL")
                bindUI()
                Log.d("chris", "Selected premier league")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}