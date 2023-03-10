package com.jsbs87.android.rickmorty.app.presentation.ui.characters

import androidx.lifecycle.MutableLiveData
import com.jsbs87.android.rickmorty.app.domain.interactors.GetCharactersUseCase
import com.jsbs87.android.rickmorty.app.domain.interactors.UseCase
import com.jsbs87.android.rickmorty.app.domain.model.Character
import com.jsbs87.android.rickmorty.app.domain.model.Characters
import com.jsbs87.android.rickmorty.app.domain.model.Info
import com.jsbs87.android.rickmorty.app.presentation.platform.BaseViewModel
import java.util.*

class CharactersCharactersViewModel(private val getCharacters: GetCharactersUseCase) : BaseViewModel() {

    var filteredElements: MutableLiveData<List<Character>> = MutableLiveData()
    var films: MutableLiveData<List<Character>> = MutableLiveData()
    var info: MutableLiveData<Info> = MutableLiveData()

    var searchText: String = ""
        set(value) {
            field = value
            filterData()
        }

    private fun filterData() {
        if (searchText.isEmpty()) {
            filteredElements.value = films.value
            filteredElements.postValue(filteredElements.value)
            return
        }
        filteredElements.value = films.value?.filter { it.name.toLowerCase(Locale.getDefault()).contains(searchText.toLowerCase(Locale.getDefault())) }
        filteredElements.postValue(filteredElements.value)
    }


    fun loadCharacters() {
        showLoading()
        getCharacters(UseCase.None) {
            hideLoading()
            it.either(::handleFailure, ::handlerFilms)
        }
    }

    private fun handlerFilms(result: Characters) {
        films.value = result.results
        info.value = result.info
    }
}