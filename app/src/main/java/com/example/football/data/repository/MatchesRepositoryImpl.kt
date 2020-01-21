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

    override suspend fun getMatches(compCode: String): List<Matche>{

        return withContext(Dispatchers.IO){
            initMatchesData(compCode)
            return@withContext matchDataDao.getCompMatches(compCode)
        }
    }

    private fun persistFetchedMatches (fetchedMatches: CompData){

        GlobalScope.launch(Dispatchers.IO) {
            for (x in fetchedMatches.matches){
                x.compCode = fetchedMatches.competition!!.code
                matchDataDao.upsert(x)
            }
        }
    }

    private suspend fun initMatchesData(compCode: String){
        if (isFetchedMatchesNeeded(ZonedDateTime.now().minusHours(1))) //Always true, for now
            fetchMatches(compCode)
    }

    private suspend fun fetchMatches(compCode: String){
        footballNetworkDataSource.fetchMatches(
            code = compCode// Get from settings later
        )
    }

    private fun isFetchedMatchesNeeded(lastFetched: ZonedDateTime) : Boolean {
        val thiryMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetched.isBefore(thiryMinutesAgo)
    }

}