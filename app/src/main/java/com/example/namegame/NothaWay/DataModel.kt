package com.example.namegame.NothaWay

import com.example.namegame.data.matchData.MatchList
import com.example.namegame.data.matchData.Matches
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*



val stringDate: String = LocalDate.now().toString()


interface FootballApi{
    @GET("competitions/2003/matches")
    suspend fun getEreMatches(@Query("dateFrom") startDate:String, @Query("dateTo") endDate:String): MatchList

    @GET("competitions/PL/matches")
    suspend fun getPLMatches(@Query("dateFrom") startDate:String, @Query("dateTo") endDate:String): MatchList

    @GET("matches/{id}")
    suspend fun getMatchById(@Path("id") id:Int): Matches
}