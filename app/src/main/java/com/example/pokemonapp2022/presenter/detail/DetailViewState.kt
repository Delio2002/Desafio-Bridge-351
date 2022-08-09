package com.example.pokemonapp2022.presenter.detail

import com.example.pokemonapp2022.presenter.detail.dataui.PokemonDetailDataUi

sealed class DetailViewState {
    object Initial: DetailViewState()
    data class Success (val data: PokemonDetailDataUi): DetailViewState()
    object Loading: DetailViewState()
    data class Error(var error: String): DetailViewState()
}