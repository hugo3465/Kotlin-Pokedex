package com.example.pokedex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
    fun PokemonCard(id: String, name: String, imageUrl: String, navController: NavController) {
    ElevatedCard(
        onClick = { navController.navigate("details/${name}") },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Row {
            Column {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = name,
                    modifier = Modifier
                        .height(90.dp)
                        .width(90.dp)
                )
            }

            Column {
                Row {
                    Text(
                        text = name,
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )

                }

//                Row {
//                    Text(
//                        text = "Tipos",
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.padding(start = 16.dp)
//                    )
//                }
            }
        }


    }
}