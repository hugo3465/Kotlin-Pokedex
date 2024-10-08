package com.example.pokedex.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
) {

    // Observar o estado da stack de navegação, sempre que houiver uma alteração vai carregar outra vez o menu
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    return navBackStackEntry.let {
        // Verificar se está no ecrã inicial (home)
        val canNavigateBack = navController.previousBackStackEntry != null


        // Existem vários Tipos de TopAppBar para brincar
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                // Only show the back arrow if it's not the root activity
                if (canNavigateBack) {
                    IconButton(onClick = {
                        navController.popBackStack() // Navegar para trás usando o NavController
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                }
            },
            // botões que eu posso clicar (convém ter até 3, se mais meto um popup)
            actions = {},
            scrollBehavior = scrollBehavior, // menu desaparecer quando é dado scroll
        )
    }

}