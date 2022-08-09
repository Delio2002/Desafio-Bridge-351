package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.model.Pokemon
import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.network.utils.ResultRemote
import kotlinx.coroutines.flow.Flow

class GetPokemonUseCase(private val repository: PokemonRepository) {
    suspend fun invoke(name: String):  Flow<ResultRemote<Pokemon>> {
        return repository.getPokemon(name)
    }
}
