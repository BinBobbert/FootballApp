package com.example.football


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.football.viewMatches.MatchPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


// First activity, pick which competition you want to see.
class MainActivity : AppCompatActivity() {
    val fAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Football")



        // Show eredivisie
        eredivisie.setOnClickListener {
            val intent = Intent(this, MatchPresenter()::class.java)
            intent.putExtra("comp", "DED")
            startActivity(intent)

        }

        // Show premier league
        premier_league.setOnClickListener {

            val intent = Intent(this, MatchPresenter()::class.java)
            intent.putExtra("comp", "PL")
            startActivity(intent)

        }

        logout.setOnClickListener {
            fAuth.signOut()

            fAuth.addAuthStateListener {
                if(fAuth.currentUser == null){
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }


        }


    }

}

