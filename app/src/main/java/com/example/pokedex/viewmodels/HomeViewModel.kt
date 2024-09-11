package com.example.pokedex.viewmodels

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.PokedexApplication
import com.example.pokedex.data.local.dao.PokemonDao
import com.example.pokedex.data.local.models.PokemonEntity
import com.example.pokedex.data.mappers.toPokemonEntityList
import com.example.pokedex.preferences.UserPreferencesRepository
import com.example.pokedex.repositories.PokemonRemoteRepository
import com.example.pokedex.utils.Constants.PAGE_SIZE
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val pokemonRemoteRepository: PokemonRemoteRepository,
    private val pokemonDao: PokemonDao
) : ViewModel() {

    private val userPreferences: UserPreferencesRepository =
        PokedexApplication.userPreferencesRepository

    /**
     * sempre que state é mudado, notifica o compose para atualizer, graças ao mutableStateOf(ScreenState()),
     * mas precisa de ser o estado inteiro e não os seus atributos, por isso que uso o state = state.copy
     */
    var state by mutableStateOf(ScreenState())
        private set


    init {
        viewModelScope.launch {
            if (userPreferences.isFirstRun()) {
                loadAllPokemonsToCache()
                userPreferences.changeFirstRunToFalse()
            }

            loadNextPokemons()
        }
    }

    private suspend fun loadAllPokemonsToCache() {
        state = state.copy(isLoading = true)

        try {
            val result = pokemonRemoteRepository.getAllPokemons()
            when (result) {
                is Resource.Success -> {
                    val results = result.data?.results?.toPokemonEntityList()

                    // insert all pokemons into the cahce, if it's not empty
                    if (!results.isNullOrEmpty()) {
                        pokemonDao.insert(results)
                    }
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = result.message!!
                    )
                }
            }
        } catch (e: Exception) {
            state = state.copy(error = e.localizedMessage)
        }

        state = state.copy(isLoading = false)

    }

    fun findPokemon(text: String) {
        if (state.isLoading) return

        viewModelScope.launch {
            state = state.copy(isLoading = true, isSearching = true)

            try {
                val results = pokemonDao.searchPokemon("$text%")

                if (results.isNotEmpty()) {
                    state = state.copy(
                        items = results,
                        page = 0 // ter que mudar isto depois, isto é muito chunga
                    )
                }
            } catch (e: Exception) {
                state = state.copy(error = e.localizedMessage)
            }

            state = state.copy(isLoading = false)
        }
    }

    fun loadNextPokemons() {
        if (state.isLoading || state.endReached || state.isSearching) return

        viewModelScope.launch {
            state = state.copy(isLoading = true)

            try {
                val results =
                    pokemonDao.getPokemonsWithLimitAndOffset(PAGE_SIZE, state.page * PAGE_SIZE)

                if (results.isNotEmpty()) {
                    state = state.copy(
                        items = state.items + results,
                        page = state.page + 1
                    )
                } else {
                    // Se a lista está vazia, então foi porque chegou ao fim
                    state = state.copy(endReached = true)
                }
            } catch (e: Exception) {
                state = state.copy(error = e.localizedMessage)
            }

            state = state.copy(isLoading = false)
        }

    }

}

data class ScreenState(
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val items: List<PokemonEntity> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false, // ainda não foi usado
    val page: Int = 0
)