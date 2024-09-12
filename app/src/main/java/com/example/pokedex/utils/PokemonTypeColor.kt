package com.example.pokedex.utils

import androidx.compose.ui.graphics.Color
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

fun pokemonTypeColor(type: String): Color? {
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