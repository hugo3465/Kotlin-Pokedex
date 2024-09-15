package com.example.pokedex.ui.partials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.SuccessResult
import com.example.pokedex.data.local.models.PokemonEntity
import com.example.pokedex.utils.Constants.DEFAULT_COLOR
import com.example.pokedex.viewmodels.HomeViewModel

@Composable
fun PokemonCard(
    pokemon: PokemonEntity,
    homeMvvm: HomeViewModel,
    navController: NavController
) {
    // Extrai o número antes da última barra no url
    val pokemonNumber = pokemon.url.substringBeforeLast("/").substringAfterLast("/")
    val pokemonImageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonNumber}.png"

    val defaultDominantColor = DEFAULT_COLOR
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    ElevatedCard(
        onClick = { navController.navigate("details/${pokemon.name}") },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(
                            dominantColor,
                            defaultDominantColor
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Column {
                AsyncImage(
                    model = pokemonImageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier
                        .height(90.dp)
                        .width(90.dp),
                    onSuccess = { painterState ->
                        val drawable = (painterState.result as SuccessResult).drawable
                        homeMvvm.calcDominantColor(drawable) { color ->
                            dominantColor = color // Store the dominant color
                        }
                    }
                )
            }

            Column {
                Row {
                    Text(
                        text = pokemon.name,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )

                }

//                Row {
//                    Text(
//                        text = "Tipos",
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.padding(start = 16.dp)
//                    )
//                }
            }
        }


    }
}