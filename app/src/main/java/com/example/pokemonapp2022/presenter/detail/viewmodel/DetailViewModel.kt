package com.example.pokemonapp2022.presenter.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp2022.domain.usecase.GetPokemonUseCase
import com.example.pokemonapp2022.network.utils.ResultRemote
import com.example.pokemonapp2022.presenter.detail.DetailViewState
import com.example.pokemonapp2022.presenter.detail.mapper.toPokemonDetailDataUi
import com.example.pokemonapp2022.presenter.home.HomeViewState
import com.example.pokemonapp2022.presenter.home.mapper.toPokemonDataUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableSharedFlow<DetailViewState>(replay = 0)
    val state: MutableSharedFlow<DetailViewState> get() = _viewState

    fun fetchPokemon(namePokemon: String) {
        viewModelScope.launch(dispatcher) {
            getPokemonUseCase.invoke(namePokemon)
                .flowOn(dispatcher)
                .onStart {
                    _viewState.emit(DetailViewState.Loading)
                }
                .catch { e ->
                    _viewState.emit(DetailViewState.Error(e.message.toString()))
                }
                .collect {
                    when (it) {
                        is ResultRemote.Success -> {
                            _viewState.emit(DetailViewState.Success(it.response.toPokemonDetailDataUi()))
                        }
                        is ResultRemote.ErrorResponse.MappedError -> {
                            _viewState.emit(DetailViewState.Error(it.error.name))
                        }
                        is ResultRemote.ErrorResponse.UnknownError -> {
                            _viewState.emit(DetailViewState.Error(it.messageError))
                        }
                    }
                }

        }
    }
}