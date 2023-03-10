package com.jsbs87.android.rickmorty.app.data.local

import com.jsbs87.android.rickmorty.app.domain.exception.Failure
import com.jsbs87.android.rickmorty.app.domain.functional.Either
import com.jsbs87.android.rickmorty.app.domain.model.Character

interface LocalDataSource {

    fun saveCharacter(character: Character): Either<Failure, Boolean>
    fun isCharacterSaved(character: Character): Boolean
    fun removeCharacterSaved(character: Character): Boolean
    fun getFavoriteCharacters(): List<Character>

}