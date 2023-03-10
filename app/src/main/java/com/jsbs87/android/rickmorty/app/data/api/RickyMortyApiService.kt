package com.jsbs87.android.rickmorty.app.data.api

import com.jsbs87.android.rickmorty.app.data.entity.CharacterEntity
import com.jsbs87.android.rickmorty.app.data.entity.CharactersEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickyMortyApiService {

    @GET("character")
    fun getCharacters(): Call<CharactersEntity>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<CharacterEntity>

}