package com.jsbs87.android.rickmorty.app.domain.di

import com.jsbs87.android.rickmorty.app.domain.interactors.GetCharactersUseCase
import com.jsbs87.android.rickmorty.app.domain.interactors.GetDetailCharacterUseCase
import org.koin.dsl.module


val domainModule = module {
    factory { GetDetailCharacterUseCase(get()) }
    factory { GetCharactersUseCase(get()) }
}
