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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior
) {
    // Get the current activity from LocalContext
//    val activity = LocalContext.current as? Activity
//    val activity: MutableLiveData<Context> = MutableLiveData(LocalContext.current)

    // Get the current activity from LocalContext
    val activity = LocalContext.current as? Activity

    // Check if this is the root activity
    val isRootActivity = activity?.isTaskRoot == true

    // Existem vários Tipos de TopAppBar para brincar
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            // Only show the back arrow if it's not the root activity
            if (!isRootActivity) {
                IconButton(onClick = { activity?.finish() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            }
        },
        // botões que eu posso clicar (convém ter até 3, se mais meto um popup)
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Edit Notes"
                )
            }
        },
        scrollBehavior = scrollBehavior, // menu desaparecer quando é dado scroll

    )
}