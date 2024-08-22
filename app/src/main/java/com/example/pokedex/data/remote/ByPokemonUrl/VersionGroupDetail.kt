package com.example.pokedex.data.remote.ByPokemonUrl

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: com.example.pokedex.data.remote.ByPokemonUrl.MoveLearnMethod,
    val version_group: com.example.pokedex.data.remote.ByPokemonUrl.VersionGroup
)