package com.jsbs87.android.rickmorty.app.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jsbs87.android.rickmorty.app.domain.exception.Failure
import com.jsbs87.android.rickmorty.app.domain.functional.Either
import com.jsbs87.android.rickmorty.app.domain.model.Character

const val KEY_FAVORITE_MOVIES = "favorites"

class LocalStorage(private val sharedPreferences: SharedPreferences) : LocalDataSource {

    override fun saveCharacter(character: Character): Either<Failure, Boolean> {
        val charactersSaved = getFavoriteCharacters().toMutableList()
        val characterSaved = charactersSaved.find { it.id == character.id }
        return if (characterSaved != null) {
            Either.Left(Failure.CharacterAlreadySaved)
        } else {
            charactersSaved.add(character)
            saveCharacterList(charactersSaved)
            Either.Right(true)
        }
    }

    override fun isCharacterSaved(character: Character): Boolean {
        return getFavoriteCharacters().toMutableList().find { it.id == character.id } != null
    }

    override fun removeCharacterSaved(character: Character): Boolean {
        val charactersSaved = getFavoriteCharacters().toMutableList()
        val characterSaved = charactersSaved.find { it.id == character.id }
        return if (characterSaved != null) {
            val resultList = charactersSaved.filter { it.id != character.id }
            saveCharacterList(resultList)
            true
        } else {
            false
        }
    }


    override fun getFavoriteCharacters(): List<Character> {
        val characters: List<Character>
        val jsonList = sharedPreferences.getString(KEY_FAVORITE_MOVIES, "")
        characters = if (jsonList!!.isEmpty()) {
            emptyList()
        } else {
            val type = object : TypeToken<List<Character>>() {}.type
            Gson().fromJson<MutableList<Character>>(jsonList, type)
        }
        return characters
    }

    private fun saveCharacterList(callLog: List<Character>) {
        val prefsEditor = sharedPreferences.edit()
        val json = Gson().toJson(callLog)
        prefsEditor.putString(KEY_FAVORITE_MOVIES, json)
        prefsEditor.apply()
    }

}