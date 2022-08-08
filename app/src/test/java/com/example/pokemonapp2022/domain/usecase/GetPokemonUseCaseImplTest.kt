package com.example.pokemonapp2022.domain.usecase

import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.data.dto.response.HomeResponse
import com.example.pokemonapp2022.data.dto.response.OfficialArtworkResponse
import com.example.pokemonapp2022.data.dto.response.OtherResponse
import com.example.pokemonapp2022.data.dto.response.PokemonResponse
import com.example.pokemonapp2022.data.dto.response.SpeciesResponse
import com.example.pokemonapp2022.data.dto.response.SpritesResponse
import com.example.pokemonapp2022.data.mappers.toPokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test


class GetPokemonUseCaseImplTest {

    lateinit var mockGetPokemonUseCaseImp: GetPokemonUseCase

    private val mockPokemonResponse =
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


    @MockK
    lateinit var mockRepository: PokemonRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockGetPokemonUseCaseImp = GetPokemonUseCaseImpl(mockRepository)
    }

    @Test
    fun `getPokemon SHOULD return pokemon WHEN resquest is sucess`() {
        runBlocking {
            coEvery { mockRepository.getPokemon("delio")}.returns(mockPokemonResponse.toPokemon())
            mockGetPokemonUseCaseImp.invoke("delio").collect() {
                assertEquals(it, mockPokemonResponse.toPokemon())
            }
        }
    }
}

