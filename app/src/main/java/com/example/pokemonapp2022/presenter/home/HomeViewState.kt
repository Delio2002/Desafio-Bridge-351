package com.example.pokemonapp2022.presenter.home
import com.example.pokemonapp2022.presenter.home.dataui.PokemonDataUi

sealed class HomeViewState {
    object Initial: HomeViewState()
    data class Success (val data: List<PokemonDataUi>): HomeViewState()
    object Loading: HomeViewState()
    data class Error(var error: String): HomeViewState()
}