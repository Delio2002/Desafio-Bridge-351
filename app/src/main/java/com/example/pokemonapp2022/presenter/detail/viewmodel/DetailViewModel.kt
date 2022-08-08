package com.example.pokemonapp2022.presenter.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp2022.domain.usecase.GetPokemonUseCase
import com.example.pokemonapp2022.domain.usecase.GetPokemonUseCaseImpl
import com.example.pokemonapp2022.presenter.detail.DetailViewState
import com.example.pokemonapp2022.presenter.detail.mapper.toPokemonDetailDataUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(private val getPokemonUseCaseImpl: GetPokemonUseCaseImpl, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val _viewState = MutableSharedFlow<DetailViewState>(replay = 0)
    val state: MutableSharedFlow<DetailViewState> get() = _viewState

     fun fetchPokemonList(name: String) {
        viewModelScope.launch(dispatcher) {
            getPokemonUseCaseImpl.invoke(name)
                .flowOn(dispatcher)
                .onStart {
                    _viewState.emit(DetailViewState.Loading(true))
                }
                .onCompletion {

                }
                .catch { e ->
                    _viewState.emit(DetailViewState.Error(e.message.toString()))
                }
                .collect {
                    _viewState.emit(DetailViewState.Success(it.toPokemonDetailDataUi()))
                }
        }
    }

}