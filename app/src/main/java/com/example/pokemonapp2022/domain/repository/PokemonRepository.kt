package com.example.pokemonapp2022.domain.repository

import com.example.pokemonapp2022.network.utils.ResultRemote
import com.example.pokemonapp2022.domain.model.PokemonList
import com.example.pokemonapp2022.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository{
   suspend fun getPokemonList(): Flow<ResultRemote<PokemonList>>
   suspend fun getPokemon(name: String): Flow<ResultRemote<Pokemon>>
}