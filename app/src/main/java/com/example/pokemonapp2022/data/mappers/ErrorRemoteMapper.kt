package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.network.utils.ResultRemote
import retrofit2.Response

fun <T> Response<T>.toErrorResponse(): ResultRemote.ErrorResponse {
    val networkError = ResultRemote.NetworkErrors.values().firstOrNull {
        it.code == this.code()
    }
    return if (networkError != null) {
        ResultRemote.ErrorResponse.MappedError(networkError)
    } else {
        ResultRemote.ErrorResponse.UnknownError(this.message())
    }
}