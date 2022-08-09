package com.example.pokemonapp2022.data.repository

import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.data.api.PokemonApi
import com.example.pokemonapp2022.network.utils.ResultRemote
import com.example.pokemonapp2022.data.mappers.toErrorResponse
import com.example.pokemonapp2022.data.mappers.toPokemon
import com.example.pokemonapp2022.data.mappers.toPokemonList
import com.example.pokemonapp2022.domain.model.PokemonList
import com.example.pokemonapp2022.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class PokemonRepositoryImpl(private val pokemonApi: PokemonApi) : PokemonRepository {

    override suspend fun getPokemon(name: String): Flow<ResultRemote<Pokemon>> {
        val response = pokemonApi.getPokemon(name)
        return flowOf(
            if (response.isSuccessful && response.body() != null) {
                ResultRemote.Success(response.body()!!.toPokemon())
            } else {
                response.toErrorResponse()
            }
        )
    }

    override suspend fun getPokemonList(): Flow<ResultRemote<PokemonList>>  {
        val response = pokemonApi.getAllPokemon()
        return flowOf(
            if (response.isSuccessful && response.body() != null) {
                ResultRemote.Success(response.body()!!.toPokemonList())
            } else {
                response.toErrorResponse()
            }
        )
    }
}





