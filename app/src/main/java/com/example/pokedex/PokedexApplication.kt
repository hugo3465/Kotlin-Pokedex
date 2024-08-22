package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.AppModule
import com.example.pokedex.di.AppModuleImpl

/**
 * Este fichiero serve unica e exclusivamente para poder acessar o AppModule em qualquer ficheiro, sem precisar crair múltiplas instâncias
 * e elas vão permanecer ativas enquanto a aplicação estiver ativa
 *
 * tive de inserir isto no androidManifest: android:name=".PokedexApplication"
 */
class PokedexApplication: Application() {

    // allow us always to access this appModule
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}