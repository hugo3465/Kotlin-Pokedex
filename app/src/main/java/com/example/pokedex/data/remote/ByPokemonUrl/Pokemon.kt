package com.example.pokedex.data.remote.ByPokemonUrl

data class Pokemon(
    val abilities: List<com.example.pokedex.data.remote.ByPokemonUrl.Ability>,
    val base_experience: Int,
    val cries: com.example.pokedex.data.remote.ByPokemonUrl.Cries,
    val forms: List<com.example.pokedex.data.remote.ByPokemonUrl.Form>,
    val game_indices: List<com.example.pokedex.data.remote.ByPokemonUrl.GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<com.example.pokedex.data.remote.ByPokemonUrl.Move>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any>,
    val past_types: List<Any>,
    val species: com.example.pokedex.data.remote.ByPokemonUrl.Species,
    val sprites: com.example.pokedex.data.remote.ByPokemonUrl.Sprites,
    val stats: List<com.example.pokedex.data.remote.ByPokemonUrl.Stat>,
    val types: List<com.example.pokedex.data.remote.ByPokemonUrl.Type>,
    val weight: Int
)