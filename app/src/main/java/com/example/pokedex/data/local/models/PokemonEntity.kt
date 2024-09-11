package com.example.pokedex.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonentity")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val url: String
)
