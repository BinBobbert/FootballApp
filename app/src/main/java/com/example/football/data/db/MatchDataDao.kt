package com.example.football.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.football.data.matchData.gsonData.Matche

@Dao
interface MatchDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(matchEntries: Matche): Long

    @Query("SELECT * FROM matches ORDER BY date, dayOfMatch, day")
    fun getAllTest(): List<Matche>

    @Query("SELECT * FROM matches WHERE compCode IN (:compCodes) ORDER BY date, dayOfMatch, day"  )
    fun getSpecificComps(compCodes: List<String>) : List<Matche>

    @Query("SELECT * FROM matches WHERE compCode = :code" )
    fun getCompMatches(code: String) : List<Matche>

}