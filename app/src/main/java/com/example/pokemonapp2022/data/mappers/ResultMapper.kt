package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.data.dto.response.ResultResponse
import com.example.pokemonapp2022.domain.model.Result

fun ResultResponse.toResult() : Result {
    return Result(name = this.name, url = this.url)
}