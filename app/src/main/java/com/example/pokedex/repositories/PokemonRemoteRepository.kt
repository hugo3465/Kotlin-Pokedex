package com.example.pokedex.repositories

import com.example.pokedex.PokedexApplication
import com.example.pokedex.data.remote.byAbilityUrl.Ability
import com.example.pokedex.data.remote.byEvolutionChainUrl.EvolutionChain
import com.example.pokedex.data.remote.byPokemonSpeciesUrl.PokemonSpecie
import com.example.pokedex.data.remote.byPokemonUrl.Pokemon
import com.example.pokedex.data.remote.byPokemonUrl.PokemonList
import com.example.pokedex.retrofit.PokemonApi
import com.example.pokedex.utils.Resource
import retrofit2.http.Path


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

    suspend fun getPokemonSpecie(pokemonNumber: Int): Resource<PokemonSpecie> {
        val response = try {
            api.getPokemonSpecie(pokemonNumber)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

    suspend fun getPokemonEvolutionChain(@Path("evoulutionChainId") evoulutionChainId: Int): Resource<EvolutionChain> {
        val response = try {
            api.getPokemonEvolutionChain(evoulutionChainId)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

}
