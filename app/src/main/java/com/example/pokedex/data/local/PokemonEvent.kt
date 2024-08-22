package com.example.pokedex.data.local

/**
 * Um evento é algo que o utilizador pode fazer, nesta interface tem os métodos
 * sobre o que oo utilizador pode interagir por vontade própria
 */
sealed interface PokemonEvent {
    object searchPokemon: PokemonEvent
    object deletePokemonData: PokemonEvent // acho que não vou usar este

    // setters podem estar qui
}