package com.example.pokemonapp2022.presenter.home.mapper

import com.example.pokemonapp2022.domain.model.PokemonItem
import com.example.pokemonapp2022.presenter.home.dataui.PokemonItemDataUi
import com.example.pokemonapp2022.presenter.utils.getPokemonPhotoURL

fun PokemonItem.toPokemonItemDataUi() : PokemonItemDataUi {
    return PokemonItemDataUi(name = this.name, imageUrl = this.url.getPokemonPhotoURL())
}