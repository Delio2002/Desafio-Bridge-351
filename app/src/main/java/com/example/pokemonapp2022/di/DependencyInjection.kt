package com.example.pokemonapp2022.di

import com.example.pokemonapp2022.data.api.PokemonApi
import com.example.pokemonapp2022.network.Service
import com.example.pokemonapp2022.data.repository.PokemonRepositoryImpl
import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.domain.usecase.GetAllPokemonUseCase
import com.example.pokemonapp2022.domain.usecase.GetPokemonUseCase
import com.example.pokemonapp2022.presenter.detail.viewmodel.DetailViewModel
import com.example.pokemonapp2022.presenter.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
   factory { Service().create(PokemonApi::class.java) }
   factory<PokemonRepository>{PokemonRepositoryImpl(get()) }
   factory { GetAllPokemonUseCase(get()) }
   factory { GetPokemonUseCase(get())}
   viewModel {HomeViewModel(get())}
   viewModel {DetailViewModel(get())}
}




