package com.example.pokemonapp2022.domain.repository

import androidx.paging.PagingData
import com.example.pokemonapp2022.network.utils.ResultRemote
import com.example.pokemonapp2022.domain.model.Pokemon
import com.example.pokemonapp2022.domain.model.PokemonItem
import kotlinx.coroutines.flow.Flow

interface PokemonRepository{
   suspend fun getPokemonList(): Flow<PagingData<PokemonItem>>
   suspend fun getPokemon(name: String): Flow<ResultRemote<Pokemon>>
}