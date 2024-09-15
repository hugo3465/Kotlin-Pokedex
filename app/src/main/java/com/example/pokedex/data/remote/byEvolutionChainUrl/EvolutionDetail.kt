package com.example.pokedex.data.remote.byEvolutionChainUrl

data class EvolutionDetail(
    val gender: Any,
    val held_item: Any,
    val item: Item,
    val known_move: Any,
    val known_move_type: KnownMoveType,
    val location: Location,
    val min_affection: Int,
    val min_beauty: Any,
    val min_happiness: Int,
    val min_level: Any,
    val needs_overworld_rain: Boolean,
    val party_species: Any,
    val party_type: Any,
    val relative_physical_stats: Any,
    val time_of_day: String,
    val trade_species: Any,
    val trigger: Trigger,
    val turn_upside_down: Boolean
)