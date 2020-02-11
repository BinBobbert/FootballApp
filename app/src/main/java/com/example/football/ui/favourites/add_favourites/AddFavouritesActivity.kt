package com.example.football.ui.favourites.add_favourites

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

import com.example.football.R
import com.example.football.ui.favourites.FavouritesViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_addfavourites.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*

class AddFavouritesActivity() : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    val fAuth = FirebaseAuth.getInstance()
    private var documentData : Map<String, Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addfavourites)


        val userName = fAuth.currentUser!!.displayName.toString()
        val docRef: DocumentReference = db.collection("gebruikers").document(userName)
        initUserData(docRef)



        pl_boolean.setOnClickListener {
            updateComp("PL", pl_boolean, docRef)
            initUserData(docRef)
        }

        ded_boolean.setOnClickListener {
            updateComp("DED", ded_boolean, docRef)
            initUserData(docRef)
        }
    }

    private fun updateComp(competition: String, compLogo: ImageView, docRef: DocumentReference){


        val bool = documentData!![competition].toString().toBoolean()

        if(!bool){
            compLogo.setImageResource(R.drawable.ic_favorite)
            docRef.update(competition, true)
        } else {
            compLogo.setImageResource(R.drawable.ic_add_favourite_24dp)
            docRef.update(competition, false)
        }
    }

    private fun initializeHearts(){
        val comps = listOf("PL", "DED")

        for (x in comps){
            when(x){
                "PL" -> if (documentData!![x].toString().toBoolean()) pl_boolean.setImageResource(R.drawable.ic_favorite)
                "DED" -> if (documentData!![x].toString().toBoolean()) ded_boolean.setImageResource(R.drawable.ic_favorite)
            }
        }
    }

    private fun initUserData(docRef: DocumentReference){

        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document!!.exists()) {
                    Log.d("chris",
                        "DocumentSnapshot data: " + document.data
                    )
                    documentData = document.data
                    initializeHearts()
                } else {
                    Log.d("chris", "No such document")
                }
            } else {
                Log.d(
                    "chris",
                    "get failed with ",
                    task.exception
                )
            }
        }
    }


}
