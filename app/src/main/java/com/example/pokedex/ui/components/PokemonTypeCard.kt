package com.example.pokedex.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.ui.theme.Bug
import com.example.pokedex.ui.theme.Dark
import com.example.pokedex.ui.theme.Dragon
import com.example.pokedex.ui.theme.Electric
import com.example.pokedex.ui.theme.Fairy
import com.example.pokedex.ui.theme.Fighting
import com.example.pokedex.ui.theme.Fire
import com.example.pokedex.ui.theme.Flying
import com.example.pokedex.ui.theme.Ghost
import com.example.pokedex.ui.theme.Grass
import com.example.pokedex.ui.theme.Ground
import com.example.pokedex.ui.theme.Ice
import com.example.pokedex.ui.theme.Normal
import com.example.pokedex.ui.theme.Poison
import com.example.pokedex.ui.theme.Psychic
import com.example.pokedex.ui.theme.Rock
import com.example.pokedex.ui.theme.Steel
import com.example.pokedex.ui.theme.Water

fun pickTypeColor(type: String): Color? {
     return when (type) {
        "normal" -> Normal
         "fighting" -> Fighting
         "flying" -> Flying
         "poison" -> Poison
         "ground" -> Ground
         "rock" -> Rock
         "bug" -> Bug
         "ghost" -> Ghost
         "steel" -> Steel
         "fire" -> Fire
         "water" -> Water
         "grass" -> Grass
         "electric" -> Electric
         "psychic" -> Psychic
         "ice" -> Ice
         "dragon" -> Dragon
         "dark" -> Dark
         "fairy" -> Fairy
          else -> {null}
     }

}

@Composable
fun PokemonTypeCard(type: String, modifier: Modifier) {
    val color = pickTypeColor(type = type)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Row {
        PokemonTypeCard("fire", Modifier.weight(1f))
        PokemonTypeCard("ghost", Modifier.weight(1f))
    }
}