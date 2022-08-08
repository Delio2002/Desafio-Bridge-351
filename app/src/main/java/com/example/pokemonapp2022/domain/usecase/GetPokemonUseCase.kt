package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface GetPokemonUseCase {
    suspend fun invoke(name: String): Flow<Pokemon>
}