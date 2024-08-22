package com.example.pokedex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coisinhas.ui.partials.NavBars.SwipeableTabRows.SwipeableTabRows
import com.example.coisinhas.ui.partials.NavBars.SwipeableTabRows.TabItem
import com.example.pokedex.PokedexApplication
import com.example.pokedex.ui.screens.PokemonDetailsTabs.MainTab
import com.example.pokedex.ui.screens.PokemonDetailsTabs.MovesTab
import com.example.pokedex.viewmodels.PokemonDetailsViewModel
import com.example.pokedex.viewmodels.viewModelFactory

@Composable
fun PokemonDetailsScreen(
    pokemonName: String,
    pokemonDetailsMvvm: PokemonDetailsViewModel = viewModel<PokemonDetailsViewModel>(
        factory = viewModelFactory {
            PokemonDetailsViewModel(
                PokedexApplication.appModule.pokemonRepository
            )
        }
    )
) {
    pokemonDetailsMvvm.getPokemon(pokemonName)
    val pokemon = pokemonDetailsMvvm.observePokemonLiveData().observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        pokemon.value?.let {

            val tabItems = listOf(
                TabItem(
                    title = "Pokemon",
                    content = { MainTab(pokemon = it, pokemonDetailsMvvm) }
                ),
                TabItem(
                    title = "Forms",
                    content = { Text(text = "Forms Content") }
                ),
                TabItem(
                    title = "Moves",
                    content = { MovesTab(it.moves)  }
                )
            )

                SwipeableTabRows(tabItems)

        } ?: run {
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

