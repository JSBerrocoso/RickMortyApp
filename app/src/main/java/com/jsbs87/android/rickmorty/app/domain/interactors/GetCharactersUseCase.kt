package com.jsbs87.android.rickmorty.app.domain.interactors

import com.jsbs87.android.rickmorty.app.data.repository.RickyMortyRepositoryImp
import com.jsbs87.android.rickmorty.app.domain.exception.Failure
import com.jsbs87.android.rickmorty.app.domain.functional.Either
import com.jsbs87.android.rickmorty.app.domain.model.Characters

class GetCharactersUseCase(private val repository: RickyMortyRepositoryImp) :
    UseCase<Characters, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, Characters> {
        return repository.getCharacters()
    }
}