package com.example.coisinhas.ui.partials.NavBars

import android.app.Activity
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
            actions = {
                if(!canNavigateBack) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Edit Notes"
                        )
                    }
                }
            },
            scrollBehavior = scrollBehavior, // menu desaparecer quando é dado scroll

        )
    }

}