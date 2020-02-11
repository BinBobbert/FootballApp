package com.example.football.ui.favourites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football.R
import com.example.football.ui.base.ScopedFragment
import com.example.football.ui.favourites.add_favourites.AddFavouritesActivity
import com.example.football.ui.home.MatchAdapter
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.toolbar.view.favourite_add
import kotlinx.android.synthetic.main.toolbar.view.toolbar_title
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FavouritesFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: FavouritesViewModelFactory by instance()
    private lateinit var favouritesViewModel: FavouritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        favouritesViewModel =
            ViewModelProvider(this, viewModelFactory).get(FavouritesViewModel::class.java)

        val tbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        tbar.favourite_add.visibility = View.VISIBLE
        tbar.toolbar_title.text = "Favourites"

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val toolbar = activity!!.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        bindUI()

        toolbar?.favourite_add?.setOnClickListener {

            val intent = Intent(this.context, AddFavouritesActivity::class.java)
            startActivity(intent)

        }


    }

    private fun bindUI() = launch{

        val matchList = favouritesViewModel.currentMatches

        //test.text = matchList.value.toString()

        matchList.observe(viewLifecycleOwner, Observer{
            if (it == null) return@Observer

            favourites_recycle.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.adapter = MatchAdapter(matchList.value!!)

            }

            favourites_progressbar.visibility = View.INVISIBLE
            favourites_recycle.visibility = View.VISIBLE
        })

    }

    override fun onDestroy() {
        val tbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        tbar.favourite_add.visibility = View.GONE
        super.onDestroy()
    }

    override fun onResume() {
        favouritesViewModel.initUserData()
        super.onResume()
    }

}