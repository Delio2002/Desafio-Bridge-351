package com.example.pokemonapp2022.presenter.detail

import com.example.pokemonapp2022.presenter.detail.dataui.PokemonDetailDataUi

sealed class DetailViewState {
    object Initial: DetailViewState()
    data class Success (val data: PokemonDetailDataUi): DetailViewState()
    data class Loading(var data: Boolean): DetailViewState()
    data class Error(var error: String): DetailViewState()
}