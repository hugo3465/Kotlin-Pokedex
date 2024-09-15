package com.example.pokedex.data.remote.byPokemonUrl

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.example.pokedex.data.remote.byPokemonUrl.MoveLearnMethod,
    val version_group: com.example.pokedex.data.remote.byPokemonUrl.VersionGroup
)