package com.example.pokedex.data.remote.ByAbilityUrl

data class Ability(
    val effect_changes: List<Any>,
    val effect_entries: List<com.example.pokedex.data.remote.ByAbilityUrl.EffectEntry>,
    val flavor_text_entries: List<com.example.pokedex.data.remote.ByAbilityUrl.FlavorTextEntry>,
    val generation: com.example.pokedex.data.remote.ByAbilityUrl.Generation,
    val id: Int,
    val is_main_series: Boolean,
    val name: String,
    val names: List<com.example.pokedex.data.remote.ByAbilityUrl.Name>,
    val pokemon: List<com.example.pokedex.data.remote.ByAbilityUrl.Pokemon>
)