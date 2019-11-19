package com.example.namegame

import android.os.Bundle
import android.util.Log.d
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button1.setOnClickListener {

            if (name1.text.toString() == "good gril"){
                inaki.setImageResource(R.drawable.inakiiii)
            } else if (name1.text.toString() == "chair gril"){
                inaki.setImageResource(R.drawable.inakiii)
            } else if (name1.text.toString() == "bush gril"){
                inaki.setImageResource(R.drawable.inakii)
            } else if (name1.text.toString() == "sitting gril"){
                inaki.setImageResource(R.drawable.inakiiiii)
            }
        }
    }


}
