package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.data.dto.response.PokeApiResponse
import com.example.pokemonapp2022.data.dto.response.ResultResponse
import com.example.pokemonapp2022.data.mappers.toPokemonList
import com.example.pokemonapp2022.network.utils.ResultRemote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetAllPokemonUseCaseTest {

    lateinit var getAllPokemonUseCase: GetAllPokemonUseCase

    private val mockPokeApiResponse = PokeApiResponse(
        count = 0,
        next = "",
        previous = "",
        results = listOf(ResultResponse("", ""))
    )

    @MockK
    lateinit var mockRepository: PokemonRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getAllPokemonUseCase = GetAllPokemonUseCase(mockRepository)
    }

    @Test
    fun `getPokemonList SHOULD emit ResultRemote Success to Domain WHEN request in api is success`() {
        runBlocking {
            //Given
            var mockResultRemote = ResultRemote.Success(mockPokeApiResponse.toPokemonList())

            //Every
            coEvery { mockRepository.getPokemonList() }.returns(flowOf(mockResultRemote))

            //Then
            getAllPokemonUseCase.invoke().collect { assertEquals(it, mockResultRemote) }
        }
    }


    @Test
    fun `getPokemonList SHOULD emit ResultRemote ErrorResponse to Domain WHEN request in api failure because of the network`() {
        runBlocking {
            //Given
            val networkError = ResultRemote.NetworkErrors.CONNECTION_SHUTDOWN

            //Every
            coEvery { mockRepository.getPokemonList() }.returns(
                flowOf(ResultRemote.ErrorResponse.MappedError(networkError))
            )

            //Then
            getAllPokemonUseCase.invoke().collect {
                assertEquals(it, ResultRemote.ErrorResponse.MappedError(networkError))
            }
        }
    }
}

