package com.example.football.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.lifecycle.Observer
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
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.football.ui.base.ViewModelFactory
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import kotlinx.android.synthetic.main.toolbar.view.*


class HomeFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: ViewModelFactory by instance()
    private lateinit var homeViewModel: HomeViewModel
    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val tbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        tbar.toolbar_title.text = "Home"

        homeViewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bindUI()
    }

    private fun bindUI() = launch{

        val matchList = homeViewModel.currentMatches
        var pos: Int = 0

        matchList.observe(viewLifecycleOwner, Observer{
            if (it == null) return@Observer


            Log.d("chris", matchList.value!![0].season!!.currentMatchday.toString())

            home_recycle.also {

                it.layoutManager = LinearLayoutManager(requireContext())
                val adapter = MatchAdapter(matchList.value!!)
                pos = adapter.getCurrentMatchday()

                it.adapter = adapter
            }

            home_recycle.scrollToPosition(pos)
            home_progressbar.visibility = View.INVISIBLE
            home_recycle.visibility = View.VISIBLE
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.comp_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}