package com.jsbs87.android.rickmorty.app.data.entity

import com.google.gson.annotations.SerializedName
import com.jsbs87.android.rickmorty.app.domain.model.Character;
data class CharacterEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String
) {
    fun toCharacter(): Character {
        return Character(id, name, status, species, type, gender, image)
    }

    companion object {
        fun empty() = CharacterEntity(0,"","","","","","")
    }
}