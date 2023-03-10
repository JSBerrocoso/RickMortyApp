package com.jsbs87.android.rickmorty.app.presentation.platform

import com.jsbs87.android.rickmorty.app.domain.exception.Failure

interface FailureHandler {
    fun onFailure(failure: Failure?)
}