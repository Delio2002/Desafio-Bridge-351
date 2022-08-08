package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.data.dto.response.PokeApiResponse
import com.example.pokemonapp2022.data.dto.response.ResultResponse
import com.example.pokemonapp2022.data.mappers.toPokemon
import com.example.pokemonapp2022.data.mappers.toPokemonList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetAllPokemonUseCaseImpTest {

    lateinit var mockGetAllPokemonUseCaseImp: GetAllPokemonUseCaseImp

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
        mockGetAllPokemonUseCaseImp = GetAllPokemonUseCaseImp(mockRepository)
    }

    @Test
    fun `getPokemonList SHOULD return mockPokeApiResponse WHEN resquest is sucess`() {
        runBlocking {
            coEvery { mockRepository.getPokemonList()}.returns(mockPokeApiResponse.toPokemonList())
            mockGetAllPokemonUseCaseImp.invoke().collect() {
                assertEquals(it, mockPokeApiResponse.toPokemonList())
            }
        }
    }
}

