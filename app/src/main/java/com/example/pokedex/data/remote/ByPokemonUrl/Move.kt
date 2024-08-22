package com.example.pokedex.data.remote.ByPokemonUrl

data class Move(
    val move: com.example.pokedex.data.remote.ByPokemonUrl.MoveX,
    val version_group_details: List<com.example.pokedex.data.remote.ByPokemonUrl.VersionGroupDetail>
)