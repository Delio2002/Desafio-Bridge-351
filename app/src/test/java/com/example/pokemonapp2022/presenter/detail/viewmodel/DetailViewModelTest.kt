package com.example.pokemonapp2022.presenter.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.pokemonapp2022.domain.model.Pokemon
import com.example.pokemonapp2022.domain.usecase.GetPokemonUseCase
import com.example.pokemonapp2022.network.utils.ResultRemote
import com.example.pokemonapp2022.presenter.detail.DetailViewState
import com.example.pokemonapp2022.presenter.detail.mapper.toPokemonDetailDataUi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

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

private val mockPokemonDetailsDataUi = mockPokemon.toPokemonDetailDataUi()


@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var viewModel: DetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockGetPokemonUseCase: GetPokemonUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(mockGetPokemonUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `fetchPokemon SHOULD emit DetailViewState Loading and Success to View WHEN receive PokemonDetails with Success`() {
        runBlocking {

            var mockResultRemote = ResultRemote.Success(mockPokemon)

            coEvery { mockGetPokemonUseCase.invoke(mockPokemonName) } returns flowOf(mockResultRemote)

            viewModel.fetchPokemon(mockPokemonName)

            viewModel.state.test {
                viewModel.fetchPokemon(mockPokemonName)
                assertEquals(DetailViewState.Loading, awaitItem())
                assertEquals(DetailViewState.Success(mockPokemonDetailsDataUi), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `fetchPokemon SHOULD emit DetailViewState Loading and Error to View WHEN receive mapped Error`() {
        runBlocking {

            val networkError = ResultRemote.NetworkErrors.CONNECTION_SHUTDOWN

            coEvery { mockGetPokemonUseCase.invoke(mockPokemonName) } returns flowOf(
                ResultRemote.ErrorResponse.MappedError(
                    networkError
                )
            )

            viewModel.fetchPokemon(mockPokemonName)

            viewModel.state.test {
                viewModel.fetchPokemon(mockPokemonName)
                assertEquals(DetailViewState.Loading, awaitItem())
                assertEquals(DetailViewState.Error(networkError.name), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}



