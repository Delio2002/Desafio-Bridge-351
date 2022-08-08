package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.data.dto.response.PokeApiResponse
import com.example.pokemonapp2022.domain.model.PokemonList

fun PokeApiResponse.toPokemonList(): PokemonList {
    return PokemonList(
        results = this.results.map { it.toResult()},
        next = this.next
    )
}