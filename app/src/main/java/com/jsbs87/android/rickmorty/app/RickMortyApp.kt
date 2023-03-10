package com.jsbs87.android.rickmorty.app

import android.app.Application
import com.jsbs87.android.rickmorty.app.data.di.dataModule
import com.jsbs87.android.rickmorty.app.domain.di.domainModule
import com.jsbs87.android.rickmorty.app.presentation.di.androidModule
import com.jsbs87.android.rickmorty.app.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(androidModule, presentationModule, domainModule, dataModule))
        }
    }
}