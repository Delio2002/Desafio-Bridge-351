package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.domain.model.PokemonList
import com.example.pokemonapp2022.network.utils.ResultRemote
import kotlinx.coroutines.flow.Flow

class GetAllPokemonUseCase(private val repository: PokemonRepository) {
    suspend fun invoke(): Flow<ResultRemote<PokemonList>> {
        return repository.getPokemonList()
    }
}