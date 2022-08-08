package com.example.pokemonapp2022.presenter.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp2022.domain.usecase.GetAllPokemonUseCase
import com.example.pokemonapp2022.domain.usecase.GetAllPokemonUseCaseImp
import com.example.pokemonapp2022.presenter.home.HomeViewState
import com.example.pokemonapp2022.presenter.home.dataui.PokemonDataUi
import com.example.pokemonapp2022.presenter.utils.getPokemonPhotoURL
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class HomeViewModel(private val getAllPokemonUseCaseImp: GetAllPokemonUseCaseImp, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val _viewState = MutableSharedFlow<HomeViewState>()
    val state: MutableSharedFlow<HomeViewState> get() = _viewState

    init {
        fetchPokemonList()
    }

    fun fetchPokemonList() {
        viewModelScope.launch(dispatcher) {
            getAllPokemonUseCaseImp.invoke()
                .flowOn(dispatcher)
                .onStart {
                    _viewState.emit(HomeViewState.Loading(true))
                }
                .onCompletion {
                   _viewState.emit(HomeViewState.Loading(false))
                }
                .catch { e ->
                    _viewState.emit(HomeViewState.Error(e.message.toString()))
                }
                .collect {
                    var pokemons = arrayListOf<PokemonDataUi>()
                    it.results.map { result ->
                        pokemons.add(
                            PokemonDataUi(
                                name = result.name,
                                imageUrl = result.url.getPokemonPhotoURL()
                            )
                        )
                    }
                    _viewState.emit(HomeViewState.Success(pokemons))
                }
        }
    }

}