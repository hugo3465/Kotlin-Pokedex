package com.example.pokedex.retrofit


import com.example.pokedex.data.remote.ByPokemonUrl.Pokemon
import com.example.pokedex.data.remote.ByPokemonUrl.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon/{pokemon}")
    suspend fun getPokemon(@Path("pokemon") pokemonName: String): Pokemon

    @GET("pokemon?limit=100000&offset=0")
    fun getAllPokemons(): Call<com.example.pokedex.data.remote.ByPokemonUrl.PokemonList>

    @GET("ability/{ability}")
    suspend fun getAbility(@Path("ability") abilityName: String): com.example.pokedex.data.remote.ByAbilityUrl.Ability

    @GET("pokemon")
    suspend fun getPokemonsFromRange(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonList
}