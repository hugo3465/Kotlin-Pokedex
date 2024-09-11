package com.example.pokedex.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.pokedex.data.local.dao.PokemonDao
import com.example.pokedex.data.local.LocalDatabase
import com.example.pokedex.repositories.PokemonRemoteRepository
import com.example.pokedex.retrofit.PokemonApi
import com.example.pokedex.utils.Constants.BASE_URL
import com.example.pokedex.utils.Constants.LOCAL_DB_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val sharedPreferences: SharedPreferences
    val pokemonApi: PokemonApi
    val pokemonDao: PokemonDao
    val pokemonRemoteRepository: PokemonRemoteRepository
    val localDatabase: LocalDatabase
}

/**
 * AppModule contém as dependências que queremos poder injetar nas classes que se vai criar
 *
 * por ter o lazy, os objetos vão ser criados na primeira que forem acessados
 */
class AppModuleImpl(
    private val appContext: Context
): AppModule {

    override val sharedPreferences: SharedPreferences by lazy {
        appContext.getSharedPreferences("myPref", Context.MODE_PRIVATE)
    }

    override val pokemonApi: PokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    override val localDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            LocalDatabase::class.java, LOCAL_DB_NAME
        ).fallbackToDestructiveMigration() // Add this to handle version changes during development
        .build()
    }

    override val pokemonDao: PokemonDao by lazy {
        localDatabase.pokemonDao
    }

    override val pokemonRemoteRepository: PokemonRemoteRepository by lazy {
        PokemonRemoteRepository(pokemonApi)
    }
}