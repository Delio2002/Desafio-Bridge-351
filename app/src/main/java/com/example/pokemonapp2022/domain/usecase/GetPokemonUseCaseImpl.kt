package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.model.Pokemon
import com.example.pokemonapp2022.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonUseCaseImpl(private val repository: PokemonRepository) : GetPokemonUseCase {
    override suspend fun invoke(name: String): Flow<Pokemon> {
        return flow { emit(repository.getPokemon(name)) }
    }
}
