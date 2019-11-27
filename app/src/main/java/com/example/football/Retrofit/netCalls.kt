package com.example.football.Retrofit

import android.util.Log
import com.example.football.data.matchData.MatchList
import com.example.football.data.matchData.Matches
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
* This Interface is used to send API requests via Retrofit
*
* The companion object create the final retrofit API
* And adds an authInterceptor with the API key
*
* Functions can be used to request API data
*
*/


interface FootballApi{
    @GET("competitions/{code}/matches")
    suspend fun getMatches(@Path("code") code:String?, @Query("dateFrom") startDate:String, @Query("dateTo") endDate:String): MatchList

    @GET("competitions/PL/matches")
    suspend fun getPLMatches(@Query("dateFrom") startDate:String, @Query("dateTo") endDate:String): MatchList

    @GET("matches/{id}")
    suspend fun getMatchById(@Path("id") id:Int): Matches

    @GET("competitions/2003/matches")
    suspend fun getAllEre(): MatchList



    companion object{
        private val authInterceptor = Interceptor {chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .build()


            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .addHeader("X-Auth-Token", "bee56d62d9334405921c3bfe4661f6a0")
                .build()

            Log.d("URL", newRequest.toString())
            chain.proceed(newRequest)

        }

        private val football_client = OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .build()

        fun retroFit(): Retrofit = Retrofit.Builder()
            .client(football_client)
            .baseUrl("https://api.football-data.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val footballAPI : FootballApi = retroFit().create(FootballApi::class.java)
    }
}