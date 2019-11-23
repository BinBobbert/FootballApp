package com.example.namegame.NothaWay

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory{

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