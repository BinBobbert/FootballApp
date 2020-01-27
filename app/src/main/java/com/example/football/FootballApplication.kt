package com.example.football

import android.app.Application
import com.example.football.data.db.MatchDatabase
import com.example.football.data.network.*
import com.example.football.data.repository.MatchesRepository
import com.example.football.data.repository.MatchesRepositoryImpl
import com.example.football.ui.base.ViewModelFactory
import com.example.football.ui.favourites.FavouritesViewModelFactory
import com.example.football.ui.favourites.add_favourites.AddFavouritesActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class FootballApplication: Application(), KodeinAware{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@FootballApplication))

        bind() from singleton { MatchDatabase(instance())}
        bind() from singleton { instance<MatchDatabase>().currentMatchDao()}
        bind<ConnectivityInterceptor>() with singleton {ConnectivityInterceptorImpl(instance())}
        bind() from singleton { FootballApi(instance())}
        bind<FootballNetworkDataSource>() with singleton {FootballNetworkDataSourceImpl(instance())}
        bind<MatchesRepository>() with singleton {MatchesRepositoryImpl(instance(), instance())}
        bind() from provider {
            ViewModelFactory(
                instance()
            )
        }
        bind() from provider { FavouritesViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
