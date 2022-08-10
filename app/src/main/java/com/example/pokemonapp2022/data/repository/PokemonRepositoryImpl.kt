package com.example.pokemonapp2022.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.data.api.PokemonApi
import com.example.pokemonapp2022.network.utils.ResultRemote
import com.example.pokemonapp2022.data.mappers.toErrorResponse
import com.example.pokemonapp2022.data.mappers.toPokemon
import com.example.pokemonapp2022.data.pagingdatasource.PokemonPagingSource
import com.example.pokemonapp2022.domain.model.Pokemon
import com.example.pokemonapp2022.domain.model.PokemonItem
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

    override suspend fun getPokemonList(): Flow<PagingData<PokemonItem>>  {
        return Pager(
            pagingSourceFactory = { PokemonPagingSource(pokemonApi) },
            config = PagingConfig(pageSize = 20)
        ).flow
    }
}





