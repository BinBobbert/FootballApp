package com.example.football.ui.favourites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.repository.MatchesRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class FavouritesViewModel (
    private val matchesRepository: MatchesRepository
): ViewModel(){
    private val _currentMatches = MutableLiveData<List<Matche>>()
    private var favouriteComps: MutableList<String> = mutableListOf()
    val db = FirebaseFirestore.getInstance()
    val fAuth = FirebaseAuth.getInstance()

    val currentMatches: LiveData<List<Matche>>
        get() = _currentMatches

    init {

        initUserData()
    }

    fun updateViewModel(){

        viewModelScope.launch {
            Log.d("chris", "matches before update: ")
            val matches = matchesRepository.getSpecificCompMatches(favouriteComps)
            Log.d("chris", "matches after update: " + matches.toString())
            _currentMatches.postValue(matches)
        }
    }

    fun initUserData(){

        val userName = fAuth.currentUser!!.displayName.toString()
        val docRef: DocumentReference = db.collection("gebruikers").document(userName)

        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document!!.exists()) {
                    Log.d("chris",
                        "DocumentSnapshot data: " + document.data
                    )
                    initFaveComps(document.data)
                    updateViewModel()

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

    fun initFaveComps(userData: Map<String, Any>?){
        val currentComps = listOf("PL", "DED")

        for (x in currentComps){
            if (userData!![x].toString().toBoolean()){
                favouriteComps.remove(x)
                favouriteComps.add(x)
            } else {
                favouriteComps.remove(x)
            }
        }

    }

}

