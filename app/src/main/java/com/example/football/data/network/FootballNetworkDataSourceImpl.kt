package com.example.football.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.network.response.CompData
import com.example.football.data.network.response.tryout.NetworkResponse
import com.example.football.internal.NoConnectivityException

class FootballNetworkDataSourceImpl(
    private val footballApi: FootballApi
) : FootballNetworkDataSource {

    private val _downloadedMatches = MutableLiveData<CompData>()

    override val downloadedMatches: LiveData<CompData>
        get() = _downloadedMatches

    override suspend fun fetchMatches(code: String) {
        try{

            val fetchedMatches:CompData = footballApi
                .getMatches(code)
            _downloadedMatches.postValue(fetchedMatches)


        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}