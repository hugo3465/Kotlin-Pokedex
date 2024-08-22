package com.example.pokedex.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.data.local.PokemonDao
import com.example.pokedex.data.local.PokemonDatabase
import com.example.pokedex.data.remote.ByPokemonUrl.Pokemon
import com.example.pokedex.repositories.PokemonRepository
import com.example.pokedex.retrofit.PokemonApi
import com.example.pokedex.utils.Constants.BASE_URL
import com.example.pokedex.utils.Constants.LOCAL_DB_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val pokemonApi: PokemonApi
    val pokemonDao: PokemonDao
    val pokemonRepository: PokemonRepository
    val pokemonDatabase: PokemonDatabase
}

/**
 * AppModule contém as dependências que queremos poder injetar nas classes que se vai criar
 *
 * por ter o lazy, os objetos vão ser criados na primeira que forem acessados
 */
class AppModuleImpl(
    private val appContext: Context
): AppModule {

    override val pokemonApi: PokemonApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    override val pokemonDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            PokemonDatabase::class.java, LOCAL_DB_NAME
        ).build()
    }

    override val pokemonDao: PokemonDao by lazy {
        pokemonDatabase.dao
    }

    override val pokemonRepository: PokemonRepository by lazy {
        PokemonRepository(pokemonApi)
    }
}