package com.example.football.data.network

import android.util.Log
import com.example.football.data.matchData.gsonData.Matche
import com.example.football.data.network.response.CompData
import com.example.football.data.network.response.tryout.NetworkResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    suspend fun getMatches(@Path("code") code:String?): CompData

    @GET("competitions/PL/matches")
    suspend fun getPLMatches(@Query("dateFrom") startDate:String, @Query("dateTo") endDate:String): Matche

    @GET("matches/{id}")
    suspend fun getMatchById(@Path("id") id:Int): Matche

    @GET("competitions/2003/matches")
    suspend fun getAllEre(): CompData

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : FootballApi{

            val authInterceptor = Interceptor {chain ->
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

            val football_client = OkHttpClient()
                .newBuilder()
                .addInterceptor(authInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                    .client(football_client)
                    .baseUrl("https://api.football-data.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .create(FootballApi::class.java)


        }

    }
}