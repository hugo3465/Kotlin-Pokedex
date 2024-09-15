package com.example.pokedex.retrofit


import com.example.pokedex.data.remote.byEvolutionChainUrl.EvolutionChain
import com.example.pokedex.data.remote.byPokemonSpeciesUrl.PokemonSpecie
import com.example.pokedex.data.remote.byPokemonUrl.Pokemon
import com.example.pokedex.data.remote.byPokemonUrl.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon?limit=100000&offset=0")
    suspend fun getAllPokemons(): PokemonList

    @GET("pokemon")
    suspend fun getPokemonsFromRange(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonList

    @GET("pokemon/{pokemon}")
    suspend fun getPokemon(@Path("pokemon") pokemonName: String): Pokemon

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecie(@Path("id") pokemonNumber: Int): PokemonSpecie

    @GET("evolution-chain/{evoulutionChainId}")
    suspend fun getPokemonEvolutionChain(@Path("evoulutionChainId") evoulutionChainId: Int): EvolutionChain
    
    @GET("ability/{ability}")
    suspend fun getAbility(@Path("ability") abilityName: String): com.example.pokedex.data.remote.byAbilityUrl.Ability


}