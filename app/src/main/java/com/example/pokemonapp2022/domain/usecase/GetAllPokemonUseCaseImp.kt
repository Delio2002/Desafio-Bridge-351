package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.domain.model.PokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllPokemonUseCaseImp(private val repository: PokemonRepository):GetAllPokemonUseCase {
    override suspend fun invoke(): Flow<PokemonList> {
        return flow { emit(repository.getPokemonList()) }
    }
}

