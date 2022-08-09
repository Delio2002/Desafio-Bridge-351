package com.example.pokemonapp2022.presenter.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.pokemonapp2022.domain.model.PokemonList
import com.example.pokemonapp2022.domain.model.Result
import com.example.pokemonapp2022.domain.usecase.GetAllPokemonUseCase
import com.example.pokemonapp2022.network.utils.ResultRemote
import com.example.pokemonapp2022.presenter.home.HomeViewState
import com.example.pokemonapp2022.presenter.home.mapper.toPokemonDataUi
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


private lateinit var viewModel: HomeViewModel

private val mockPokemonList = PokemonList(
    results = listOf(Result(name = "teste", url = "testkjhkhkh/88876asdsaddsfs.com")),
    next = "",
)

private val mockPokemonDetailsDataUi = mockPokemonList.results.map { it.toPokemonDataUi() }

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var viewModel: HomeViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockGetAllPokemonUseCase: GetAllPokemonUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(mockGetAllPokemonUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `fetchPokemonList SHOULD emit HomeViewState Loading and Success to View WHEN receive Pokemon list with Success`() {
        runBlocking {

            var mockResultRemote = ResultRemote.Success(mockPokemonList)

            coEvery { mockGetAllPokemonUseCase.invoke() } returns flowOf(mockResultRemote)

            viewModel.fetchPokemonList()
            viewModel.state.test {
                viewModel.fetchPokemonList()
                assertEquals(HomeViewState.Loading, awaitItem())
                assertEquals(HomeViewState.Success(mockPokemonDetailsDataUi), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `fetchPokemonList SHOULD emit HomeViewState Loading and Error to View WHEN receive mapped Error`() {
        runBlocking {

            val networkError = ResultRemote.NetworkErrors.CONNECTION_SHUTDOWN

            coEvery { mockGetAllPokemonUseCase.invoke() } returns flowOf(
                ResultRemote.ErrorResponse.MappedError(
                    networkError
                )
            )

            viewModel.fetchPokemonList()

            viewModel.state.test {
                viewModel.fetchPokemonList()
                assertEquals(HomeViewState.Loading, awaitItem())
                assertEquals(HomeViewState.Error(networkError.name), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
    }

}





