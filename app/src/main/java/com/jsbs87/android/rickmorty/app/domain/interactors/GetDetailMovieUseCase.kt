package com.jsbs87.android.rickmorty.app.domain.interactors

import com.jsbs87.android.rickmorty.app.data.repository.RickyMortyRepositoryImp
import com.jsbs87.android.rickmorty.app.domain.exception.Failure
import com.jsbs87.android.rickmorty.app.domain.functional.Either

class GetDetailCharacterUseCase(private val repository: RickyMortyRepositoryImp) :
    UseCase< com.jsbs87.android.rickmorty.app.domain.model.Character, GetDetailCharacterUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, com.jsbs87.android.rickmorty.app.domain.model.Character> {
        return repository.getDetailCharacter(params.id)
    }

    class Params(val id: Int)
}