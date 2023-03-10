package com.jsbs87.android.rickmorty.app.data.repository

import android.util.Log
import com.jsbs87.android.rickmorty.app.data.api.RickyMortyApiService
import com.jsbs87.android.rickmorty.app.data.entity.CharacterEntity
import com.jsbs87.android.rickmorty.app.data.entity.CharactersEntity
import com.jsbs87.android.rickmorty.app.data.local.LocalDataSource
import com.jsbs87.android.rickmorty.app.data.utils.NetworkHandler
import com.jsbs87.android.rickmorty.app.domain.RickyMortyRepository
import com.jsbs87.android.rickmorty.app.domain.exception.Failure
import com.jsbs87.android.rickmorty.app.domain.functional.Either
import com.jsbs87.android.rickmorty.app.domain.model.Characters
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call

class RickyMortyRepositoryImp(
    private val apiService: RickyMortyApiService,
    private val networkHandler: NetworkHandler,
    private val dataSource: LocalDataSource
) : RickyMortyRepository {

    override suspend fun getCharacters(): Either<Failure, Characters> {
        return when (networkHandler.isInternetAvailable()) {
            true ->
                request(
                    apiService.getCharacters(),
                    {filmEntity -> filmEntity.toCharacters()}, CharactersEntity.empty()
                )
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun getDetailCharacter(id: Int): Either<Failure, com.jsbs87.android.rickmorty.app.domain.model.Character> {
        return when (networkHandler.isInternetAvailable()) {
            true -> request(apiService.getCharacter(id), { it.toCharacter() }, CharacterEntity.empty())
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(getFailure(response.errorBody()))
                else -> Either.Left(getFailure(response.errorBody()))
            }
        } catch (exception: Throwable) {
            Log.e(exception.javaClass.simpleName, "Request ${call.request().url}")
            Either.Left(Failure.ServerError)
        }
    }

    private fun getFailure(response: ResponseBody?): Failure {
        return try {
            val stringBody = response?.string()

            val failure =
                Failure.FeatureFailure.getFromCode(getErrorCodeFromBody(stringBody))
                    .apply {
                        message = stringBody
                    }
            failure
        } catch (e: Exception) {
            Failure.ServerError
        }
    }

    @Throws(Exception::class)
    fun getErrorCodeFromBody(stringBody: String?) =
        JSONObject(stringBody).getJSONObject("error").getInt("code")
}