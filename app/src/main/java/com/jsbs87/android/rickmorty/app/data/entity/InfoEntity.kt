package com.jsbs87.android.rickmorty.app.data.entity

import com.jsbs87.android.rickmorty.app.domain.model.Info

data class InfoEntity(
    val count: Int,
    val pages: Int,
    val next: String?
) {
    fun toInfo(): Info {
        return Info(count, pages, next)
    }
}