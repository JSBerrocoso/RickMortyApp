package com.jsbs87.android.rickmorty.app.data.entity

import com.jsbs87.android.rickmorty.app.domain.model.Characters

data class CharactersEntity(
    val info: InfoEntity?,
    val results: List<CharacterEntity>?
) {
    fun toCharacters() = Characters(
            info?.toInfo(),
            results?.map { it.toCharacter()}?: emptyList(),
        )


    companion object {
        fun empty() = CharactersEntity(
            null, emptyList()
        )
    }
}