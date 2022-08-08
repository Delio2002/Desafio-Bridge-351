package com.example.pokemonapp2022.presenter.detail.dataui
import java.io.Serializable

data class PokemonDetailDataUi(
    val abilities: List<AbilityDataUi>,
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val photo: String,
    val weight: Int,
    val types: List<TypeDataUi>,
):Serializable


