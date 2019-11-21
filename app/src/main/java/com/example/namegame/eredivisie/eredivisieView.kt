package com.example.namegame.eredivisie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.namegame.R
import kotlinx.android.synthetic.main.activity_eredivisie.*
import kotlinx.android.synthetic.main.content_eredivisie.*

class EredivisieView : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eredivisie)
        setSupportActionBar(toolbar)


    }


}