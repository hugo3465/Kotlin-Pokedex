package com.example.pokedex.data.mappers

import com.example.pokedex.data.local.PokemonEntity
import com.example.pokedex.data.remote.ByPokemonUrl.Result

fun Result.toPokemonEntity(): PokemonEntity {
    // Extrai o número antes da última barra no url
    val pokemonNumber = url.substringBeforeLast("/").substringAfterLast("/").toInt()

    return PokemonEntity(
        id = pokemonNumber,
        name = name,
        url = url
    )
}

fun List<Result>.toPokemonEntityList(): List<PokemonEntity> {
    return map { result ->
        PokemonEntity(
            name = result.name,
            url = result.url
        )
    }
}