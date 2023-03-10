package com.jsbs87.android.rickmorty.app.presentation

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.rickmorty.app.AndroidTest
import com.jsbs87.android.rickmorty.app.domain.exception.Failure
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseViewModel
import org.amshove.kluent.shouldBeInstanceOf

import org.junit.Test
import org.robolectric.annotation.Config

@Config(manifest=Config.NONE)
class BaseViewModelTest : AndroidTest() {

    @Test
    fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()

        viewModel.handleError(Failure.NetworkConnection)

        val failure = viewModel.failure
        val error = viewModel.failure.value

        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf Failure.NetworkConnection::class.java
    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}