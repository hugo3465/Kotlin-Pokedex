package com.example.pokedex.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.local.models.PokemonEntity

/**
 * suspend functions permitem executar sem bloquear o programa
 */
@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity")
    suspend fun getAllPokemons(): List<PokemonEntity>

    @Query("Select * from pokemonentity where name LIKE :query")
    suspend fun searchPokemon(query:String): List<PokemonEntity>

    @Query("SELECT * FROM pokemonentity LIMIT :limit OFFSET :offset")
    suspend fun getPokemonsWithLimitAndOffset(limit: Int, offset: Int): List<PokemonEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE) // @Upsert faz o mesmo
    suspend fun insert(pokemons: List<PokemonEntity>): List<Long>

    @Delete
    suspend fun delete(pokemons: List<PokemonEntity>): Int

    @Query("DELETE FROM pokemonentity")
    suspend fun deleteAll()
    
}