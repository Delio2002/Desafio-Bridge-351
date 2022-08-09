package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.data.dto.response.PokemonResponse
import com.example.pokemonapp2022.domain.model.Pokemon


fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        abilities = this.abilities.map { it.toAbility() },
        base_experience = this.base_experience,
        height = this.height,
        id = this.id,
        name = this.name,
        photo = this.sprites.other.home.front_default,
        weight = this.weight,
        types = this.types.map { it.toType() }
    )
}
