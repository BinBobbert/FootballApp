package com.example.football.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.football.data.matchData.gsonData.Matche

@Database(
    entities = [Matche::class],
    version = 12
)
abstract class MatchDatabase : RoomDatabase(){
    abstract fun currentMatchDao(): MatchDataDao

    companion object{
        @Volatile private var instance: MatchDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MatchDatabase::class.java, "matchData.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}