package com.example.pokemonapp2022.presenter.home.mapper

import com.example.pokemonapp2022.domain.model.Result
import com.example.pokemonapp2022.presenter.home.dataui.PokemonDataUi
import com.example.pokemonapp2022.presenter.utils.getPokemonPhotoURL

fun Result.toPokemonDataUi() : PokemonDataUi {
    return PokemonDataUi(name = this.name, imageUrl = this.url.getPokemonPhotoURL())
}