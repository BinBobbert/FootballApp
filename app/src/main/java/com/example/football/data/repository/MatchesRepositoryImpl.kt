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

    private var buffer = ZonedDateTime.now().plusMinutes(5)

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

    override suspend fun getAllMatches(): List<Matche> {
        return withContext(Dispatchers.IO){
            initAllData()
            return@withContext matchDataDao.getAllTest()
        }
    }

    override suspend fun getSpecificCompMatches(compCodes: List<String>): List<Matche> {
        return withContext(Dispatchers.IO){
            initAllData()
            return@withContext matchDataDao.getSpecificComps(compCodes)
        }
    }

    private fun persistFetchedMatches (fetchedMatches: CompData){

        GlobalScope.launch(Dispatchers.IO) {
            for (x in fetchedMatches.matches){
                x.compCode = fetchedMatches.competition!!.code
                x.day = x.utcDate!!.substring(8, 10).toInt()
                x.hour = x.utcDate!!.substring(11, 13).toInt()
                x.date = x.utcDate!!.substring(0, 10)
                matchDataDao.upsert(x)
            }
        }
    }

    private suspend fun initMatchesData(compCode: String){
        if (isFetchedMatchesNeeded()) //Always true, for now
            fetchMatches(compCode)
    }

    private suspend fun initAllData(){
        if (isFetchedMatchesNeeded()){
            fetchMatches("DED")
            fetchMatches("PL")
            fetchMatches("SA")
        }
    }

    private suspend fun fetchMatches(compCode: String){
        footballNetworkDataSource.fetchMatches(
            code = compCode// Get from settings later
        )
    }

    private fun isFetchedMatchesNeeded() : Boolean {
        val timeNow = ZonedDateTime.now()

        if(timeNow.minute > buffer.minute){
            buffer = timeNow.plusMinutes(5)
            return true
        }

        return false
    }

}