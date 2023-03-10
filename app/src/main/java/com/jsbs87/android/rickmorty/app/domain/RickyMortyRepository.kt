package com.jsbs87.android.rickmorty.app.domain

import com.jsbs87.android.rickmorty.app.domain.exception.Failure
import com.jsbs87.android.rickmorty.app.domain.functional.Either
import com.jsbs87.android.rickmorty.app.domain.model.Characters

interface RickyMortyRepository {

    suspend fun getCharacters(): Either<Failure, Characters>
    suspend fun getDetailCharacter(id: Int): Either<Failure, com.jsbs87.android.rickmorty.app.domain.model.Character>


}