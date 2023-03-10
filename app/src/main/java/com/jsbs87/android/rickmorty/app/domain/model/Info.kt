package com.jsbs87.android.rickmorty.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?
): Parcelable {
}