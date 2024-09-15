package com.example.pokedex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coisinhas.ui.partials.NavBars.SwipeableTabRows.SwipeableTabRows
import com.example.coisinhas.ui.partials.NavBars.SwipeableTabRows.TabItem
import com.example.pokedex.PokedexApplication
import com.example.pokedex.ui.screens.pokemonDetailsTabs.MainTab
import com.example.pokedex.ui.screens.pokemonDetailsTabs.MovesTab
import com.example.pokedex.viewmodels.PokemonDetailsViewModel
import com.example.pokedex.utils.viewModelFactory

@Composable
fun PokemonDetailsScreen(
    pokemonName: String,
    pokemonDetailsMvvm: PokemonDetailsViewModel = viewModel<PokemonDetailsViewModel>(
        factory = viewModelFactory {
            PokemonDetailsViewModel(
                PokedexApplication.appModule.pokemonRemoteRepository,
                pokemonName
            )
        }
    )
) {
    val state = pokemonDetailsMvvm.state
    val pokemon = state.pokemon

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        if(!state.isLoading) {

            val tabItems = listOf(
                TabItem(
                    title = "Pokemon",
                    content = {
                        if (pokemon != null) {
                            MainTab(pokemon = pokemon, pokemonDetailsMvvm)
                        }
                    }
                ),
                TabItem(
                    title = "Forms",
                    content = { Text(text = "Forms Content") }
                ),
                TabItem(
                    title = "Moves",
                    content = {
                        if (pokemon != null) {
                            MovesTab(pokemon.moves)
                        }
                    }
                )
            )

                SwipeableTabRows(tabItems)

        } else {
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Text(text = "Loading...", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

