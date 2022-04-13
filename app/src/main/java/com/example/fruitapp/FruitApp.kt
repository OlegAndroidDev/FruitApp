package com.example.fruitapp

import android.app.Application
import com.example.fruitapp.di.networkModule
import com.example.fruitapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FruitApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FruitApp)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}