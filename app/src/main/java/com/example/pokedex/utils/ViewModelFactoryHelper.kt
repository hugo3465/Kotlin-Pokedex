package com.example.pokedex.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//fun <VM: ViewModel> viewModelFactory(initializer: () -> VM): ViewModelProvider.Factory {
//    return object : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return initializer as T
//        }
//    }
//}

fun <VM : ViewModel> viewModelFactory(initializer: () -> VM): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val viewModel = initializer()
            if (modelClass.isAssignableFrom(viewModel::class.java)) {
                return viewModel as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}