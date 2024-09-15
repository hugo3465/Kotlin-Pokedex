package com.example.pokedex.data.remote.byPokemonUrl

data class PokemonList(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<com.example.pokedex.data.remote.byPokemonUrl.Result>
)