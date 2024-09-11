package com.example.pokedex.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.remote.ByPokemonUrl.Pokemon
import com.example.pokedex.repositories.PokemonRemoteRepository
import com.example.pokedex.utils.Resource
import kotlinx.coroutines.launch


class PokemonDetailsViewModel(
    private val repository: PokemonRemoteRepository
) : ViewModel() {

    private var pokemonLiveData = MutableLiveData<com.example.pokedex.data.remote.ByPokemonUrl.Pokemon?>()
    private val abilitiesLiveData = mutableMapOf<String, MutableLiveData<com.example.pokedex.data.remote.ByAbilityUrl.Ability>>()

    fun getPokemon(pokemonName: String) {
//        RetrofitInstance.api.getPokemon(pokemonName).enqueue(object : Callback<com.example.pokedex.data.remote.ByPokemonUrl.Pokemon> {
//            override fun onResponse(call: Call<com.example.pokedex.data.remote.ByPokemonUrl.Pokemon>, response: Response<com.example.pokedex.data.remote.ByPokemonUrl.Pokemon>) {
//                if (response.body() != null) {
//                    val pokemon: com.example.pokedex.data.remote.ByPokemonUrl.Pokemon = response.body()!!
//                    pokemonLiveData.value = pokemon
//                } else {
//                    Log.d("Pokemon Details View Model", "No body on getPokemon()")
//                    return
//                }
//            }
//
//            override fun onFailure(call: Call<com.example.pokedex.data.remote.ByPokemonUrl.Pokemon>, t: Throwable) {
//                Log.d("Pokemon Details ViewModel", t.message.toString())
//
//            }
//        })

        viewModelScope.launch {
            val result = repository.getPokemon(pokemonName)
            when(result) {
                is Resource.Success -> {
                    val results = result.data

                    if(results == null) {
                        Log.d("Pokemon Details View Model", "No body on getPokemon()")
                        return@launch
                    }


                    pokemonLiveData.value = results


                }

                is Resource.Error -> {
                    Log.d("Pokemon Details ViewModel", result.message.toString() )
                }
            }
        }
    }

    fun getAbility(abilityName: String) {
        viewModelScope.launch {
            val result = repository.getAbiliy(abilityName)
            when (result) {
                is Resource.Success -> {
                    val results = result.data

                    if(results == null) {
                        Log.d("Pokemon Details View Model", "No body on getAbility()")
                        return@launch
                    }

                    abilitiesLiveData[abilityName]?.value = results
                }

                is Resource.Error -> {
                    Log.d("Pokemon Details ViewModel", result.message.toString())
                }
            }
        }
    }

    fun observePokemonLiveData(): MutableLiveData<Pokemon?> {
        return pokemonLiveData
    }

    fun observeAbilitiesLiveData(abilityName: String): LiveData<com.example.pokedex.data.remote.ByAbilityUrl.Ability> {
        if (!abilitiesLiveData.containsKey(abilityName)) {
            abilitiesLiveData[abilityName] = MutableLiveData() // inicializa caso não tenha sido inicializado
        }

        return abilitiesLiveData[abilityName]!!
    }
}