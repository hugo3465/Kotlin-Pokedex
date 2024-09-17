package com.example.pokedex.utils


/**
 * Estrutura de dados que guaurda a árvore de evoluções de um pokémon,
 * juntamente com alguns atributos essenciais
 */
class EvolutionTree(
    val root: EvolutionNode
) {
    data class EvolutionNode(
        var name: String,
        var url: String,
        var evolutions: List<EvolutionNode> = emptyList()
    )

    fun getFirst(): EvolutionNode {
        return root
    }

    fun addEvolutionTo(node: EvolutionNode) {
        val evolution = findPokemonByName(node.name)

        if (evolution != null) {
            evolution.evolutions += node
        }
    }

    fun findPokemonByName(pokemonName: String, evolution: EvolutionNode = root): EvolutionNode? {
        evolution.evolutions.forEach { evolution ->
            if(evolution.name == pokemonName) {
                return evolution
            }

            findPokemonByName(pokemonName, evolution)
        }

        return null
    }
}