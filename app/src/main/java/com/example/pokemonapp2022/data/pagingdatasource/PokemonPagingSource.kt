package com.example.pokemonapp2022.data.pagingdatasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemonapp2022.data.api.PokemonApi
import com.example.pokemonapp2022.data.mappers.toResult
import com.example.pokemonapp2022.domain.model.PokemonItem

class PokemonPagingSource(private val pokemonApi: PokemonApi) : PagingSource<Int, PokemonItem>() {
    @androidx.paging.ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, PokemonItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItem> {
        return try {
            val page = params.key ?: 1
            val numberItems = 19
            val offset = (page * numberItems) - numberItems

            val response = pokemonApi.getAllPokemon(offset)

            LoadResult.Page(
                data = response!!.results.map { it.toResult() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response!!.next.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}