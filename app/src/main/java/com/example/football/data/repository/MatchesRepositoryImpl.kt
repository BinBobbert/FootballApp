package com.example.football.data.repository

import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.football.data.db.MatchDataDao
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.network.FootballNetworkDataSource
import com.example.football.data.network.response.CompData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class MatchesRepositoryImpl(
    private val matchDataDao : MatchDataDao,
    private val footballNetworkDataSource: FootballNetworkDataSource
) : MatchesRepository {

    init {

        footballNetworkDataSource.downloadedMatches.observeForever { newMatches ->

            persistFetchedMatches(newMatches)
        }

    }

    override suspend fun getMatches(): List<Matche>{

        return withContext(Dispatchers.IO){
            initMatchesData()
            return@withContext matchDataDao.getAllTest()
        }
    }

    private fun persistFetchedMatches (fetchedMatches: CompData){

        GlobalScope.launch(Dispatchers.IO) {
            for (x in fetchedMatches.matches){
                matchDataDao.upsert(x)
            }
        }
    }

    private suspend fun initMatchesData(){
        if (isFetchedMatchesNeeded(ZonedDateTime.now().minusHours(1))) //Always true, for now
            fetchMatches()
    }

    private suspend fun fetchMatches(){
        footballNetworkDataSource.fetchMatches(
            code = "DED",
            startDate = "2019-08-02", // Get from settings later
            endDate = "2020-05-20"
        )
    }

    private fun isFetchedMatchesNeeded(lastFetched: ZonedDateTime) : Boolean {
        val thiryMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetched.isBefore(thiryMinutesAgo)
    }

}