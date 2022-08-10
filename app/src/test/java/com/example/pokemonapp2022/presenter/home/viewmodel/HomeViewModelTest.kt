package com.example.pokemonapp2022.presenter.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.pokemonapp2022.domain.model.PokemonList
import com.example.pokemonapp2022.domain.model.PokemonItem
import com.example.pokemonapp2022.domain.usecase.GetAllPokemonUseCase
import com.example.pokemonapp2022.network.utils.ResultRemote
import com.example.pokemonapp2022.presenter.home.HomeViewState
import com.example.pokemonapp2022.presenter.home.mapper.toPokemonItemDataUi
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

private val mockPokemonItemList = listOf(PokemonItem(name = "teste", url = "testkjhkhkh/88876asdsaddsfs.com"))

private val mockPokemonItemDataUiList = mockPokemonItemList.map { it.toPokemonItemDataUi() }

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
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `fetchPokemonList SHOULD emit HomeViewState Loading and Success to View`() {
        runBlocking {
            coEvery { mockGetAllPokemonUseCase.invoke() } returns flowOf(PagingData.from(mockPokemonItemList))
            viewModel = HomeViewModel(mockGetAllPokemonUseCase, testDispatcher)
            viewModel.state.test {
                viewModel = HomeViewModel(mockGetAllPokemonUseCase, testDispatcher)
                assertEquals(HomeViewState.Loading, awaitItem())
                assertEquals(HomeViewState.Success(PagingData.from(mockPokemonItemDataUiList)), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
    }

}





