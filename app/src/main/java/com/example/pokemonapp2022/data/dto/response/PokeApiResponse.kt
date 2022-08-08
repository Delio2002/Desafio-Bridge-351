package com.example.pokemonapp2022.data.dto.response



data class PokeApiResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ResultResponse>
)
