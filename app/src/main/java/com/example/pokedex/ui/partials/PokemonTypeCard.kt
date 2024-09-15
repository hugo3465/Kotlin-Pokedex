package com.example.pokedex.ui.partials

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.utils.pokemonTypeColor


@Composable
fun PokemonTypeCard(type: String, modifier: Modifier) {
    val color = pokemonTypeColor(type = type)
        ?: throw Exception("Não foi provido nenhum tipo, o o tipo foi mal escrito no card do mesmo!")

    // Convert the first character to uppercase and concatenate it with the rest of the string
    val formattedType = type.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        modifier = modifier,
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = formattedType,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black, // Border color
                            offset = Offset(0f, 0f), // No offset, centered shadow
                            blurRadius = 3f // Adjust this to make the border thicker or thinner
                        )
                    )
                )
            }
        }
    )
}