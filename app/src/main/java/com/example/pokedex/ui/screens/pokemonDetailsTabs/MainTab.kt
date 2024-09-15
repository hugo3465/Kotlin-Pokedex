package com.example.pokedex.ui.screens.pokemonDetailsTabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.breens.beetablescompose.BeeTablesCompose
import com.example.pokedex.data.remote.byPokemonUrl.Pokemon
import com.example.pokedex.ui.partials.PokemonTypeCard
import com.example.pokedex.viewmodels.PokemonDetailsViewModel

@Composable
fun MainTab(
    pokemon: com.example.pokedex.data.remote.byPokemonUrl.Pokemon,
    pokemonDetailsMvvm: PokemonDetailsViewModel
) {
    PokemonImageCard(pokemon)

    PokemonTypes(types = pokemon.types)

    Spacer(modifier = Modifier.height(20.dp))

    // Tabela de Status
    StatsTable(pokemon.stats.toList())

    Spacer(modifier = Modifier.height(20.dp))

    // Abilities
    Text(text = "Abilities:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    HorizontalDivider(
        color = Color.Red,
        thickness = 2.dp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    Abilities(abilities = pokemon.abilities, pokemonDetailsMvvm)
}

@Composable
private fun PokemonTypes(types: List<com.example.pokedex.data.remote.byPokemonUrl.Type>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp) // Padding around the entire row
    ) {
        types.forEach { type ->
            PokemonTypeCard(
                type = type.type.name,
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            )
        }
    }
}

@Composable
private fun PokemonImageCard(pokemon: com.example.pokedex.data.remote.byPokemonUrl.Pokemon) {
    // Box to centralize content
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Image
            AsyncImage(
                model = pokemon.sprites.front_default,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .height(220.dp)
                    .width(220.dp)
            )

            Text(
                text = pokemon.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
private fun Abilities(
    abilities: List<com.example.pokedex.data.remote.byPokemonUrl.Ability>,
    pokemonDetailsMvvm: PokemonDetailsViewModel
) {

    val state = pokemonDetailsMvvm.state


    state.abilities?.forEach { ability ->

        val abilityName = ability.key
        val abilityData = ability.value
        val isAbilityHidden = state.pokemon?.let { isAbilityHidden(abilityName, it) }

        if (!state.isLoading) {
            val abilityDescription =
                getAbilityDescriptionFromList(abilityData.effect_entries, "en")
            val abilityModifier = Modifier.padding(vertical = 4.dp)

            Row {

                Column(
                    Modifier
                        .requiredWidth(90.dp)
                ) {
                    Text(
                        text = "${abilityName}: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Left,
                        modifier = abilityModifier
                    )
                    if (isAbilityHidden == true) {
                        Text(
                            text = "(Hidden)",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Left,
                            color = Color.Gray
                        )
                    }

                }

                Text(
                    text = abilityDescription,
                    fontSize = 15.sp,
                    modifier = abilityModifier
                )
            }

        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }


    }
}

@Composable
private fun StatsTable(pokemonStats: List<com.example.pokedex.data.remote.byPokemonUrl.Stat>) {
    val tableHeaders = listOf("HP", "ATK", "DEF", "S.ATK", "S.DEF", "SPEED")

    // Extract base stats
    val bs = pokemonStats.map { it.base_stat }
    val listOfBaseStats = listOf(PokemonStats(bs[0], bs[1], bs[2], bs[3], bs[4], bs[5]))


    BeeTablesCompose(data = listOfBaseStats, headerTableTitles = tableHeaders)
}

private fun getAbilityDescriptionFromList(
    entries: List<com.example.pokedex.data.remote.byAbilityUrl.EffectEntry>,
    language: String
): String {
    for (abilityDescription in entries) {
        if (abilityDescription.language.name == language) {
            return abilityDescription.effect
        }
    }

    return " not found "
}

private fun isAbilityHidden(abiliyName: String, pokemon: Pokemon): Boolean {
    pokemon.abilities.forEach { ability ->
        if(ability.ability.name == abiliyName) {
            return ability.is_hidden
        }
    }

    return false
}

data class PokemonStats(
    val hp: Int,
    val atk: Int,
    val def: Int,
    val sAtk: Int,
    val sDef: Int,
    val speed: Int
) {}