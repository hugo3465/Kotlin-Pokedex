package com.example.pokedex.ui.screens.PokemonDetailsTabs

import androidx.compose.runtime.Composable
import com.breens.beetablescompose.BeeTablesCompose

@Composable
fun MovesTab(moves: List<com.example.pokedex.data.remote.ByPokemonUrl.Move>) {
    val tableHeaders = listOf("Level / Method", "Name")

    // Extract the values I want from moves
    val moveEntries = moves.map { move ->
        var firstVersionGroupDetail = move.version_group_details.first()

        if(firstVersionGroupDetail.level_learned_at == 0) {
            WantedMoveData(
                learnedAt = firstVersionGroupDetail.move_learn_method.name,
                name = move.move.name
            )
        } else {
            WantedMoveData(
                learnedAt = firstVersionGroupDetail.level_learned_at.toString(),
                name = move.move.name
            )
        }
    }.sortedBy { it.learnedAt }// Sort by level


    BeeTablesCompose(data = moveEntries, headerTableTitles = tableHeaders)
}

data class WantedMoveData(
    val learnedAt: String,
    val name: String
){}