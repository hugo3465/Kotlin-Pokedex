package com.example.pokedex.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.remote.byAbilityUrl.Ability
import com.example.pokedex.data.remote.byEvolutionChainUrl.EvolutionChain
import com.example.pokedex.data.remote.byPokemonSpeciesUrl.PokemonSpecie
import com.example.pokedex.data.remote.byPokemonUrl.Pokemon
import com.example.pokedex.repositories.PokemonRemoteRepository
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.launch


class PokemonDetailsViewModel(
    private val repository: PokemonRemoteRepository,
    pokemonName: String
) : ViewModel() {

    var state by mutableStateOf(State())
        private set

    init {
        viewModelScope.launch {
            loadPokemon(pokemonName)

            loadAllPokemonAbilities()

            state.pokemon?.let { loadPokemonSpecie(it.id) }

            // Extrai o número antes da última barra no url
            val pokemonEvolutionChainNumber = state.pokemonSpecies?.evolution_chain?.url?.substringBeforeLast("/")
                ?.substringAfterLast("/")?.toInt()

            if (pokemonEvolutionChainNumber != null) {
                loadPokemonEvolutionChain(pokemonEvolutionChainNumber)
            }
        }
    }



    private suspend fun loadPokemon(pokemonName: String) {
        state = state.copy(isLoading = true)


        val result = repository.getPokemon(pokemonName)
        when (result) {
            is Resource.Success -> {
                val results = result.data

                if (results == null) {
                    Log.d("Pokemon Details View Model", "No body on loadPokemon()")
                    return
                }


                state = state.copy(pokemon = results)


            }

            is Resource.Error -> {
                Log.d("Pokemon Details ViewModel", result.message.toString())
            }
        }

        state = state.copy(isLoading = false)
    }

    private suspend fun loadAllPokemonAbilities() {
        state.pokemon?.abilities?.forEach { ability: com.example.pokedex.data.remote.byPokemonUrl.Ability ->
            getAbility(ability.ability.name)
        }
    }

    private suspend fun getAbility(abilityName: String) {
        state = state.copy(isLoading = true)

        val result = repository.getAbiliy(abilityName)
        when (result) {
            is Resource.Success -> {
                val results = result.data

                if (results == null) {
                    Log.d("Pokemon Details View Model", "No body on getAbility()")
                    return
                }


                // Cria um novo Map com a abilidade nova mais as existentes
                val updatedAbilities = state.pokemonAbilities?.toMutableMap().apply {
                    this?.set(abilityName, results)
                }

                // Atualiza o Map das abilidades
                state = state.copy(pokemonAbilities = updatedAbilities)

            }

            is Resource.Error -> {
                Log.d("Pokemon Details ViewModel", result.message.toString())
            }
        }

        state = state.copy(isLoading = false)
    }

    private suspend fun loadPokemonSpecie(pokemonNumber: Int) {
        state = state.copy(isLoading = true)

        val result = state.pokemon?.let { repository.getPokemonSpecie(pokemonNumber) }
        when (result) {
            is Resource.Success -> {
                val results = result.data

                if (results == null) {
                    Log.d("Pokemon Details View Model", "No body on loadPokemonSpecie()")
                    return
                }


                state = state.copy(pokemonSpecies = results)

            }

            is Resource.Error -> {
                Log.d("Pokemon Details ViewModel", result.message.toString())
            }

            null -> state = state.copy(error = "got null on loadPokemonSpecie()")
        }

        state = state.copy(isLoading = false)
    }

    private suspend fun loadPokemonEvolutionChain(pokemonEvolutionChainNumber: Int) {
        state = state.copy(isLoading = true)

        val result = state.pokemon?.let { repository.getPokemonEvolutionChain(pokemonEvolutionChainNumber) }
        when (result) {
            is Resource.Success -> {
                val results = result.data

                if (results == null) {
                    Log.d("Pokemon Details View Model", "No body on loadPokemonEvolutionChain()")
                    return
                }


                state = state.copy(pokemonEvolutionChain = results)

            }

            is Resource.Error -> {
                Log.d("Pokemon Details ViewModel", result.message.toString())
            }

            null -> state = state.copy(error = "got null on loadPokemonEvolutionChain()")
        }

        state = state.copy(isLoading = false)
    }

}

data class State(
    val pokemon: Pokemon? = null,
    val pokemonAbilities: Map<String, Ability>? = emptyMap(),
    val pokemonSpecies: PokemonSpecie? = null,
    val pokemonEvolutionChain: EvolutionChain? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)