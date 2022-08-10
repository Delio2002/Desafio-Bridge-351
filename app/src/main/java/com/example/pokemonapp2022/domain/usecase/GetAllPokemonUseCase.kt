package com.example.pokemonapp2022.domain.usecase

import androidx.paging.PagingData
import com.example.pokemonapp2022.domain.model.PokemonItem
import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.domain.model.PokemonList
import com.example.pokemonapp2022.network.utils.ResultRemote
import kotlinx.coroutines.flow.Flow

class GetAllPokemonUseCase(private val repository: PokemonRepository) {
    suspend fun invoke(): Flow<PagingData<PokemonItem>> {
        return repository.getPokemonList()
    }
}