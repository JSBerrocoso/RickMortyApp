package com.jsbs87.android.rickmorty.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Characters(
    val info: Info?,
    val results: List<Character>?
) : Parcelable {
}