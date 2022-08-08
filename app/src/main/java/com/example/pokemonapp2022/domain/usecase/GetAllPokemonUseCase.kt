package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.model.PokemonList
import kotlinx.coroutines.flow.Flow

interface GetAllPokemonUseCase {
    suspend fun invoke(): Flow<PokemonList>
}