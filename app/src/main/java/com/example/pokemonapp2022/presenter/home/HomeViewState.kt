package com.example.pokemonapp2022.presenter.home
import androidx.paging.PagingData
import com.example.pokemonapp2022.presenter.home.dataui.PokemonItemDataUi


sealed class HomeViewState {
    object Initial: HomeViewState()
    data class Success (val data: PagingData<PokemonItemDataUi> ): HomeViewState()
    object Loading: HomeViewState()
    data class Error(var error: String): HomeViewState()
}