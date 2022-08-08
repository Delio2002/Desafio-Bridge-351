package com.example.pokemonapp2022.domain.repository

import com.example.pokemonapp2022.domain.model.PokemonList
import com.example.pokemonapp2022.domain.model.Pokemon

interface PokemonRepository{
   suspend fun getPokemonList(): PokemonList
   suspend fun getPokemon(name: String): Pokemon
}