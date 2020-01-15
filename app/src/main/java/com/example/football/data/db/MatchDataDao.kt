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

    @Query("SELECT * FROM matches WHERE keyz = :id")
    fun getMatche(id: Int): Matche

    @Query("SELECT * FROM matches")
    fun getAllTest(): List<Matche>
}