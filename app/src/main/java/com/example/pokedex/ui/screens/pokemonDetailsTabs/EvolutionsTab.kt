package com.example.pokedex.ui.screens.pokemonDetailsTabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.data.remote.byEvolutionChainUrl.EvolutionChain
import com.example.pokedex.data.remote.byEvolutionChainUrl.EvolvesTo
import com.example.pokedex.utils.Constants.POKEMON_IMAGE_URL

@Composable
fun EvolutionsTab(evolutionChain: EvolutionChain?) {
    val evolutions = mutableListOf<Evolution>()

    if (evolutionChain != null) {
        evolutions += Evolution(
            evolutionChain.chain.species.name,
            evolutionChain.chain.species.url
        )
        evolutions += getAllEvolution(evolutionChain.chain.evolves_to)

        // Display the evolution names and images in a horizontal scrollable list
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(evolutions.size) { index ->
                val evolution = evolutions[index]
                val pokemonEvolutionNumber = evolution.url.substringBeforeLast("/").substringAfterLast("/")

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    AsyncImage(
                        model = "$POKEMON_IMAGE_URL/${pokemonEvolutionNumber}.png",
                        contentDescription = evolution.name,
                        modifier = Modifier
                            .height(90.dp)
                            .width(90.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = evolution.name.capitalize())
                }
            }
        }
    } else {
        Text(text = "No evolution data available")
    }
}

fun getAllEvolution(chain: List<EvolvesTo>): List<Evolution> {
    val evolutions = mutableListOf<Evolution>()

    chain.forEach { evolutionChain ->
        evolutions += Evolution(
            evolutionChain.species.name,
            evolutionChain.species.url
        )

        if (evolutionChain.evolves_to.isNotEmpty()) {
            evolutions += getAllEvolution(evolutionChain.evolves_to)
        }

    }

    return evolutions
}

data class Evolution(
    val name: String,
    val url: String
)
