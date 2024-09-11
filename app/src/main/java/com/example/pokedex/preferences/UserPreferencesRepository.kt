package com.example.pokedex.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.pokedex.PokedexApplication

class UserPreferencesRepository(
    private val sharedPreferences: SharedPreferences
) {

    /**
     * Nome da sharedPreference que indica se é a primeira vez que o utilizador está a
     * correr a aplicação
     */
    private val FIRST_RUN_KEY = "firstRun"

    fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean(FIRST_RUN_KEY, true)
    }

    /**
     * Vai mudar o status do firstRun para false, se jánão o estiver
     */
    fun changeFirstRunToFalse() {
        if (sharedPreferences.getBoolean(FIRST_RUN_KEY, true)) {
            sharedPreferences.edit().putBoolean(FIRST_RUN_KEY, false).apply()
        }
    }

}