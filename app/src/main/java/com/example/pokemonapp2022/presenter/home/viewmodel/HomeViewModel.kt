package com.example.pokemonapp2022.presenter.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.example.pokemonapp2022.domain.usecase.GetAllPokemonUseCase
import com.example.pokemonapp2022.presenter.home.HomeViewState
import com.example.pokemonapp2022.presenter.home.mapper.toPokemonItemDataUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableSharedFlow<HomeViewState>()
    val state: MutableSharedFlow<HomeViewState> get() = _viewState

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch(dispatcher) {
            getAllPokemonUseCase.invoke()
                .onStart {
                    _viewState.emit(HomeViewState.Loading)
                }
                .catch { e ->
                    _viewState.emit(HomeViewState.Error(e.message.toString()))
                }
                .collect {
                    _viewState.emit(HomeViewState.Success(it.map { it.toPokemonItemDataUi() }))
                }

        }
    }

}