package com.example.pokedex.data.remote.byPokemonUrl

data class Move(
    val move: com.example.pokedex.data.remote.byPokemonUrl.MoveX,
    val version_group_details: List<com.example.pokedex.data.remote.byPokemonUrl.VersionGroupDetail>
)