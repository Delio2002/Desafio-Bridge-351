package com.example.pokemonapp2022.data.repository

import com.example.pokemonapp2022.domain.repository.PokemonRepository
import com.example.pokemonapp2022.data.api.PokemonApi
import com.example.pokemonapp2022.data.mappers.toPokemon
import com.example.pokemonapp2022.data.mappers.toPokemonList
import com.example.pokemonapp2022.domain.model.PokemonList
import com.example.pokemonapp2022.domain.model.Pokemon


class PokemonRepositoryImpl(private val pokemonApi: PokemonApi) : PokemonRepository {

    override suspend fun getPokemon(name: String): Pokemon {
        return pokemonApi.getPokemon(name).toPokemon()
    }

    override suspend fun getPokemonList(): PokemonList {
        return pokemonApi.getAllPokemon().toPokemonList()
    }
}


