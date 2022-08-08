package com.example.pokemonapp2022.presenter.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.pokemonapp2022.data.dto.response.HomeResponse
import com.example.pokemonapp2022.data.dto.response.OfficialArtworkResponse
import com.example.pokemonapp2022.data.dto.response.OtherResponse
import com.example.pokemonapp2022.data.dto.response.PokemonResponse
import com.example.pokemonapp2022.data.dto.response.SpeciesResponse
import com.example.pokemonapp2022.data.dto.response.SpritesResponse
import com.example.pokemonapp2022.data.mappers.toPokemon
import com.example.pokemonapp2022.domain.usecase.GetPokemonUseCaseImpl
import com.example.pokemonapp2022.presenter.detail.DetailViewState
import com.example.pokemonapp2022.presenter.detail.mapper.toPokemonDetailDataUi
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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

val mockPokemonName = "delio"
val mockPokemonResponse =
    PokemonResponse(
        abilities = listOf(),
        base_experience = 2,
        height = 2,
        id = 2,
        is_default = true,
        location_area_encounters = "",
        name = "delio",
        order = 2,
        past_types = listOf(),
        species = SpeciesResponse("", ""),
        sprites = SpritesResponse(
            back_default = "",
            back_female = "",
            back_shiny = "",
            back_shiny_female = "",
            front_default = "",
            front_female = "",
            front_shiny = "",
            front_shiny_female = "",
            other = OtherResponse(
                home = HomeResponse(
                    front_default = "",
                    front_female = "",
                    front_shiny = "",
                    front_shiny_female = "",
                ),
                officialartwork = OfficialArtworkResponse(
                    front_default = ""
                )
            )
        ),
        stats = listOf(),
        types = listOf(),
        weight = 2
    )


@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var viewModel: DetailViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @MockK
    lateinit var getPokemonUseCase: GetPokemonUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(getPokemonUseCase, testDispatcher)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getPokemon() {
        runBlocking {

            coEvery {getPokemonUseCase.invoke(mockPokemonName) } returns flowOf(
                mockPokemonResponse.toPokemon()
            )

            viewModel.state.test {
                viewModel.fetchPokemonList(mockPokemonName)
                assertEquals(DetailViewState.Loading(true), awaitItem())
                assertEquals(DetailViewState.Success(mockPokemonResponse.toPokemon().toPokemonDetailDataUi()), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}



