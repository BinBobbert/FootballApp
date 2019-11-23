package com.example.namegame

import android.content.Intent
import android.os.Bundle
import android.util.Log.d

import androidx.appcompat.app.AppCompatActivity

import com.example.namegame.eredivisie.EredivisiePresenter
import com.example.namegame.eredivisie.PLPresenter


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        eredivisie.setOnClickListener {
            val intent = Intent(this, EredivisiePresenter::class.java)
            startActivity(intent)

        }

        premier_league.setOnClickListener {
            val intent = Intent(this, PLPresenter::class.java)
            startActivity(intent)

        }
    }

}
