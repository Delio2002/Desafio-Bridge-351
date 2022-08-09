package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.domain.model.Pokemon
import com.example.pokemonapp2022.network.utils.ResultRemote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test


class GetPokemonUseCaseTest {

    var mockPokemonName = "delio"
    private val mockPokemon = Pokemon(
        abilities = listOf(),
        base_experience = 2,
        height = 2,
        id = 2,
        name = "delio",
        types = listOf(),
        weight = 2,
        photo = "asdas/assad",
    )

    lateinit var getPokemonUseCase: GetPokemonUseCase


    @MockK
    lateinit var mockRepository: PokemonRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getPokemonUseCase = GetPokemonUseCase(mockRepository)
    }

    @Test
    fun `getPokemon SHOULD emit ResultRemote Success to Domain WHEN request in api is success`() {
        runBlocking {
            //Given
            var mockResultRemote = ResultRemote.Success(mockPokemon)

            //Every
            coEvery { mockRepository.getPokemon(mockPokemonName) }.returns(flowOf(mockResultRemote))

            //Then
            getPokemonUseCase.invoke(mockPokemonName).collect() { assertEquals(it, mockResultRemote) }

        }
    }

    @Test
    fun `getPokemon SHOULD emit ResultRemote ErrorResponse to Domain WHEN request in api failure because of the network`() {
        runBlocking {
            //Given
            val networkError = ResultRemote.NetworkErrors.CONNECTION_SHUTDOWN

            //Every
            coEvery { mockRepository.getPokemon(mockPokemonName) }.returns(
                flowOf(ResultRemote.ErrorResponse.MappedError(networkError))
            )

            //Then
            getPokemonUseCase.invoke(mockPokemonName).collect {
                assertEquals(it, ResultRemote.ErrorResponse.MappedError(networkError))
            }
        }
    }
}

