package com.example.pokedex.data.remote.byAbilityUrl

data class Ability(
    val effect_changes: List<Any>,
    val effect_entries: List<com.example.pokedex.data.remote.byAbilityUrl.EffectEntry>,
    val flavor_text_entries: List<com.example.pokedex.data.remote.byAbilityUrl.FlavorTextEntry>,
    val generation: com.example.pokedex.data.remote.byAbilityUrl.Generation,
    val id: Int,
    val is_main_series: Boolean,
    val name: String,
    val names: List<com.example.pokedex.data.remote.byAbilityUrl.Name>,
    val pokemon: List<com.example.pokedex.data.remote.byAbilityUrl.Pokemon>
)