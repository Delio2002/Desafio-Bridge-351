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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

private val mockPokemonList = PokemonList(
    results = listOf(Result(name = "teste", url = "testkjhkhkh/88876asdsaddsfs.com")),
    next = "",
)

private val mockPokemonListDataUi = mockPokemonList.results.map { it.toPokemonDataUi() }

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
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun GetAllPokemon() {
        runBlocking {

            var mockResultRemote = ResultRemote.Success(mockPokemonList)

            coEvery { mockGetAllPokemonUseCase.invoke() } returns flowOf(mockResultRemote)

            viewModel.state.test {
                viewModel = HomeViewModel(mockGetAllPokemonUseCase, testDispatcher)
                assertEquals(HomeViewState.Loading, awaitItem())
                assertEquals(HomeViewState.Success(mockPokemonListDataUi), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}


