package com.example.pokedex.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.remote.byAbilityUrl.Ability
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
            getPokemon(pokemonName)
            getAllPokemonAbilities()
        }
    }

    private suspend fun getPokemon(pokemonName: String) {
        state = state.copy(isLoading = true)


        val result = repository.getPokemon(pokemonName)
        when (result) {
            is Resource.Success -> {
                val results = result.data

                if (results == null) {
                    Log.d("Pokemon Details View Model", "No body on getPokemon()")
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

    private suspend fun getAllPokemonAbilities() {
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
                val updatedAbilities = state.abilities?.toMutableMap().apply {
                    this?.set(abilityName, results)
                }

                // Atualiza o Map das abilidades
                state = state.copy(abilities = updatedAbilities)

            }

            is Resource.Error -> {
                Log.d("Pokemon Details ViewModel", result.message.toString())
            }
        }

        state = state.copy(isLoading = false)
    }

}

data class State(
    val pokemon: Pokemon? = null,
    val abilities: Map<String, Ability>? = emptyMap(),
    val isLoading: Boolean = false
)