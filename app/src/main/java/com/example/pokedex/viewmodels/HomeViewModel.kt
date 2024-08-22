package com.example.pokedex.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.local.PokemonDao
import com.example.pokedex.data.local.PokemonEntity
import com.example.pokedex.data.mappers.toPokemonEntityList
import com.example.pokedex.data.remote.ByPokemonUrl.PokemonList
import com.example.pokedex.repositories.PokemonRepository
import com.example.pokedex.utils.Constants.PAGE_SIZE
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val repository: PokemonRepository,
    private val dao: PokemonDao
) : ViewModel() {

    /**
     * sempre que state é mudado, notifica o compose para atualizer, graças ao mutableStateOf(ScreenState()),
     * mas precisa de ser o estado inteiro e não os seus atributos, por isso que uso o state = state.copy
     */
    var state by mutableStateOf(ScreenState())
        private set

    init {
//        getPokemonsFromCache()
        loadNextPokemons()
    }

    fun loadNextPokemons() {
        if (state.isLoading || state.endReached) return

        viewModelScope.launch {
            state = state.copy(isLoading = true)

            val result = repository.getPokemonFromRange(PAGE_SIZE, state.page * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    val results = result.data?.results?.toPokemonEntityList()

                    if (!results.isNullOrEmpty()) {
                        // insert data in the current list
                        state = state.copy(
                            page = state.page + 1,
                            items = state.items + results
                        )

                        // Insert data into the database on a background thread
                        viewModelScope.launch(Dispatchers.IO) {
                            dao.insert(results)
                        }
                    } else {
                        state = state.copy(
                            endReached = true,
                        )
                    }
                }

                is Resource.Error -> {
                    state = state.copy(
                        error = result.message!!
                    )
                }
            }
            state = state.copy(isLoading = false)
        }

    }

    /**
     * TODO funciona mas não como eu quero
     */
    private fun getPokemonsFromCache() {
        // Observe LiveData from the database
        dao.getAllPokemons().observeForever { cachedPokemons ->
            if (cachedPokemons.isNotEmpty()) {
                state = state.copy(
                    items = cachedPokemons,
                    isLoading = false,
                    page = cachedPokemons.size / 20 // calcula a quantidade de páginas
                )

            } else {
                loadNextPokemons() // se não conseguiu dar load de nada vai fazer o pedido inicial pela API
            }
        }
    }


}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<PokemonEntity> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false, // ainda não foi usado
    val page: Int = 0
)