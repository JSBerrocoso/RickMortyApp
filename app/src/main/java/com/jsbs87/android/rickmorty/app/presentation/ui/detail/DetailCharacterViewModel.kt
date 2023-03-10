package com.jsbs87.android.rickmorty.app.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.rickmorty.app.domain.interactors.GetDetailCharacterUseCase
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseViewModel

class DetailCharacterViewModel(
    private val getFilms: GetDetailCharacterUseCase
) : BaseViewModel() {

    var character: MutableLiveData<com.jsbs87.android.rickmorty.app.domain.model.Character> = MutableLiveData()
    var externalId: MutableLiveData<Int> = MutableLiveData()

    fun loadDetail() {
        externalId.value?.let { GetDetailCharacterUseCase.Params(it) }?.let { it ->
            getFilms(it) {
                it.either(::handleFailure, ::handlerDetailCharacter)
            }
        }
    }

    private fun handlerDetailCharacter(resultCharacter: com.jsbs87.android.rickmorty.app.domain.model.Character) {
        character.value = resultCharacter
    }

}