package com.example.pokedex.repositories

import com.example.pokedex.PokedexApplication
import com.example.pokedex.data.remote.ByAbilityUrl.Ability
import com.example.pokedex.data.remote.ByPokemonUrl.Pokemon
import com.example.pokedex.data.remote.ByPokemonUrl.PokemonList
import com.example.pokedex.retrofit.PokemonApi
import com.example.pokedex.utils.Resource


class PokemonRemoteRepository(
    private val api: PokemonApi = PokedexApplication.appModule.pokemonApi
) {

    suspend fun getPokemonFromRange(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonsFromRange(limit, offset)
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemon(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemon(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

    suspend fun getAllPokemons(): Resource<PokemonList> {
        val response = try {
            api.getAllPokemons()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

    suspend fun getAbiliy(abilityName: String): Resource<Ability> {
        val response = try {
            api.getAbility(abilityName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

}
