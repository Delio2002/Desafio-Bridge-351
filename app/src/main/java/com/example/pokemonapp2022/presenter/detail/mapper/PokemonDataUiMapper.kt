package com.example.pokemonapp2022.presenter.detail.mapper

import com.example.pokemonapp2022.data.dto.response.PokemonResponse
import com.example.pokemonapp2022.domain.model.Pokemon
import com.example.pokemonapp2022.presenter.detail.dataui.PokemonDetailDataUi


fun Pokemon.toPokemonDetailDataUi(): PokemonDetailDataUi {
    return PokemonDetailDataUi(
        abilities = this.abilities.map { it.ToAbilityDataUi() },
        base_experience = this.base_experience,
        height = this.height,
        id = this.id,
        name = this.name,
        photo = this.photo,
        weight = this.weight,
        types = this.types.map { it.toTypeDataUi() }
    )
}