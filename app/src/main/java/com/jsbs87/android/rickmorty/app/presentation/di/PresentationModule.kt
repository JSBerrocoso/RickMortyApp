package com.jsbs87.android.rickmorty.app.presentation.di

import com.jsbs87.android.rickmorty.app.presentation.ui.characters.CharactersCharactersViewModel
import com.jsbs87.android.rickmorty.app.presentation.ui.characters.CharactersFragment
import com.jsbs87.android.rickmorty.app.presentation.ui.detail.DetailCharacterFragment
import com.jsbs87.android.rickmorty.app.presentation.ui.detail.DetailCharacterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    scope(named<CharactersFragment>()) {
        viewModel { CharactersCharactersViewModel(get()) }
    }

    scope(named<DetailCharacterFragment>()) {
        viewModel { DetailCharacterViewModel(get()) }
    }

}
