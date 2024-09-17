package com.example.pokedex.ui.screens.pokemonDetailsTabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokedex.data.remote.byEvolutionChainUrl.EvolutionChain
import com.example.pokedex.data.remote.byEvolutionChainUrl.EvolvesTo
import com.example.pokedex.utils.Constants.POKEMON_IMAGE_URL
import com.example.pokedex.utils.EvolutionTree

@Composable
fun EvolutionsTab(evolutionChain: EvolutionChain?) {
    if (evolutionChain != null) {

        val rootNode = EvolutionTree.EvolutionNode(
            evolutionChain.chain.species.name,
            evolutionChain.chain.species.url
        )

        // Construir a árvore de evoluções
        val evolutionTree = buildEvolutionTree(rootNode, evolutionChain.chain.evolves_to)

        // Display the evolution tree recursively
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            EvolutionTreeDisplay(evolutionTree.root)
        }
    } else {
        Text(text = "No evolution data available")
    }
}

@Composable
fun EvolutionTreeDisplay(node: EvolutionTree.EvolutionNode) {
    // extrair o número do pokemon a partir do rld que a api dá
    val pokemonEvolutionNumber = node.url.substringBeforeLast("/").substringAfterLast("/")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        AsyncImage(
            model = "$POKEMON_IMAGE_URL/${pokemonEvolutionNumber}.png",
            contentDescription = node.name,
            modifier = Modifier
                .height(90.dp)
                .width(90.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = node.name.capitalize())

        // Recursively display evolutions (children)
        node.evolutions.forEach { childNode ->
            EvolutionTreeDisplay(childNode)
        }
    }
}

fun buildEvolutionTree(node: EvolutionTree.EvolutionNode, chain: List<EvolvesTo>): EvolutionTree {
    chain.forEach { evolution ->
        node.evolutions += EvolutionTree.EvolutionNode(
            evolution.species.name,
            evolution.species.url
        )

        if(evolution.evolves_to.isNotEmpty()) {
            buildEvolutionTree(node.evolutions.last(), evolution.evolves_to)
        }
    }

    return EvolutionTree(node)
}
