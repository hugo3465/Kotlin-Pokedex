package com.example.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coisinhas.ui.partials.NavBars.MyTopAppBar
import com.example.pokedex.ui.screens.HomeScreen
import com.example.pokedex.ui.screens.PokemonDetailsScreen
import com.example.pokedex.viewmodels.HomeViewModel
import com.example.pokedex.viewmodels.viewModelFactory

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PokedexApp()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexApp() {

    // MUITO IMPORTANTE PARA O TOP APP BAR
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior() // existem 3 destes para brincar

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection), // MUITO IMPORTANTE PARA O TOPBAR
        topBar = { MyTopAppBar("Pokédex", scrollBehavior) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            // 1. Create a NavController to manage navigation
            val navController = rememberNavController()

            // 2. Set up a NavHost to hold different composable destinations (screens) (rotas)
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(top = 30.dp)
            ) {
                // 3. Define the composable for the "home" route (parecem rotas)
                composable("home") {
                    HomeScreen(navController)
                }
                // 4. Define the composable for the "details" route
                composable("details/{pokemonName}") { backStackEntry ->
                    // Retrieve the argument from the backStackEntry
                    val pokemonName = backStackEntry.arguments?.getString("pokemonName")

                    // Pass the argument to the PokemonDetailsScreen
                    pokemonName?.let {
                        PokemonDetailsScreen(
                            pokemonName = it
                        )
                    }
                }
            }
        }


    }
}
