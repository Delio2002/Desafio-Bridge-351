package com.example.pokemonapp2022

import android.app.Application
import com.example.pokemonapp2022.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}