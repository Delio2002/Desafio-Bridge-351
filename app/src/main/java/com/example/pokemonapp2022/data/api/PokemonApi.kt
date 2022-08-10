package com.example.pokemonapp2022.data.api

import com.example.pokemonapp2022.data.dto.response.PokeApiResponse
import com.example.pokemonapp2022.data.dto.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon?limit=20")
    suspend fun getAllPokemon(
        @Query("offset") size: Int
    ): PokeApiResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Response<PokemonResponse>
}