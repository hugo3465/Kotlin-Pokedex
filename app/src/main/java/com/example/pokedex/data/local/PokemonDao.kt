package com.example.pokedex.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * suspend functions permitem executar sem bloquear o programa
 */
@Dao
interface PokemonDao {

//    @Upsert // insere se não existirem na BD
//    suspend fun upsertAll(pokemons: List<PokemonEntity>)


//    @Query("SELECT * FROM pokemonentity")
//    fun getPokemons(): List<PokemonEntity>

    @Query("SELECT * FROM pokemonentity")
    fun getAllPokemons(): LiveData<List<PokemonEntity>> // live data serve para usar assincronismo e não executar na main thread

    @Query("Select * from pokemonentity where name LIKE :query")
    fun searchPokemon(query:String): LiveData<List<PokemonEntity>>

    @Query("SELECT * FROM pokemonentity LIMIT :limit OFFSET :offset")
    fun getPokemonsWithLimitAndOffset(limit: Int, offset: Int): LiveData<List<PokemonEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE) // @Upsert faz o mesmo
    suspend fun insert(pokemons: List<PokemonEntity>): List<Long>

    @Delete
    suspend fun delete(pokemons: List<PokemonEntity>): Int

    @Query("DELETE FROM pokemonentity")
    suspend fun deleteAll()

    // posso meter finds aqui
}