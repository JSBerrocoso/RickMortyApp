package com.jsbs87.android.rickmorty.app.domain.exception

import android.util.Log

sealed class Failure(var message: String? = null) {
    private val TAG: String = "RickMortyApp"

    init {
        if (message != null) Log.e(TAG, message!!)
    }

    object NetworkConnection : Failure()
    object ServerError : Failure()
    object SaveCharacterError : Failure()
    object GetFavoriteCharactersError : Failure()
    object IsCharacterFavoriteError : Failure()
    object SaveFavoriteCharactersError : Failure()
    object DeleteFavoriteCharactersError : Failure()
    object CharacterAlreadySaved : Failure()

    abstract class FeatureFailure(message: String? = null) : Failure(message) {

        companion object {
            fun getFromCode(code: Int): FeatureFailure {
                return when (code) {
                    10000 -> InvalidCredentials()
                    else -> GenericFailure(code)
                }
            }
        }
    }

    class GenericFailure(val code: Int = -1, message: String? = null) : FeatureFailure(message)

    class InvalidCredentials : FeatureFailure()

}
