package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.data.dto.response.ResultResponse
import com.example.pokemonapp2022.domain.model.PokemonItem

fun ResultResponse.toResult() : PokemonItem {
    return PokemonItem(name = this.name, url = this.url)
}