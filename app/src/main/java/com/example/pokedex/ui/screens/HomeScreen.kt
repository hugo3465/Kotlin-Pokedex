package com.example.pokedex.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedex.PokedexApplication
import com.example.pokedex.ui.components.PokemonListItem
import com.example.pokedex.viewmodels.HomeViewModel
import com.example.pokedex.viewmodels.viewModelFactory

/**
 *  com o hiltNavGraphViewModel()  os parametros são passados automaticamente para o viewModel, parametros esses que têm de estar especificados no appModule
 * o hiltNavGraphViewModel() cria uma instância para o viewModel se este já não tenha sido criado anteriormente
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    navController: NavController,
    homeMvvm: HomeViewModel = viewModel<HomeViewModel>(
        factory = viewModelFactory {
            HomeViewModel(
                PokedexApplication.appModule.pokemonRepository,
                PokedexApplication.appModule.pokemonDao
            )
        }
    )
) {
    val state = homeMvvm.state
    val pokemons = state.items


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        
//        SearchBar(
//            query = "Pokemon Name...",
//            onQueryChange = ,
//            onSearch = ,
//            active = true,
//            onActiveChange =,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {}
        
        items(pokemons.size) { i ->
            // load more pokemons if end reached and is not loading more data
            if (i == pokemons.size - 1 && !state.endReached && !state.isLoading) {
                homeMvvm.loadNextPokemons()
            }

            // pokemon card
            PokemonListItem(pokemon = pokemons[i], navController)
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }

                Spacer(modifier = Modifier.padding(top = 300.dp))
            }
        }

    }
}