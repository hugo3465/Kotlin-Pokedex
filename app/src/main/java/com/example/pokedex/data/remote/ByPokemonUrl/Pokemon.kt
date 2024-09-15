package com.example.pokedex.data.remote.byPokemonUrl

data class Pokemon(
    val abilities: List<com.example.pokedex.data.remote.byPokemonUrl.Ability>,
    val base_experience: Int,
    val cries: com.example.pokedex.data.remote.byPokemonUrl.Cries,
    val forms: List<com.example.pokedex.data.remote.byPokemonUrl.Form>,
    val game_indices: List<com.example.pokedex.data.remote.byPokemonUrl.GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<com.example.pokedex.data.remote.byPokemonUrl.Move>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any>,
    val past_types: List<Any>,
    val species: com.example.pokedex.data.remote.byPokemonUrl.Species,
    val sprites: com.example.pokedex.data.remote.byPokemonUrl.Sprites,
    val stats: List<com.example.pokedex.data.remote.byPokemonUrl.Stat>,
    val types: List<com.example.pokedex.data.remote.byPokemonUrl.Type>,
    val weight: Int
)