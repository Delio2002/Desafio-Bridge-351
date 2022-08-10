package com.example.pokemonapp2022.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.domain.model.PokemonItem
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

    private val mockPokemonItem = PokemonItem(
        name = "delio",
        url = "dsfds/saasdasasd"
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

            coEvery { mockRepository.getPokemonList() }.returns(flowOf(PagingData.from(listOf(mockPokemonItem))))

            getAllPokemonUseCase.invoke().collect{
                it.map {
                    assertEquals(it, mockPokemonItem)
                }
            }
        }
    }


}

