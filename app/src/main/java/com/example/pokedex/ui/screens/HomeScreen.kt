package com.example.pokedex.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokedex.PokedexApplication
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
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
                PokedexApplication.appModule.pokemonRemoteRepository,
                PokedexApplication.appModule.pokemonDao
            )
        }
    )
) {
    val state = homeMvvm.state
    val pokemons = state.items

    // para a searchBar
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Column {
        if(homeMvvm.state.error?.isNotBlank() == true) {
           Text(text = homeMvvm.state.error.toString(), color = Color.Red)
        }
        
        SearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = { active = false },
            active = active,
            onActiveChange = {

            },
            placeholder = {
                Text(text = "Pokemon Name...")
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {}

        Spacer(modifier = Modifier.height(5.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
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





}