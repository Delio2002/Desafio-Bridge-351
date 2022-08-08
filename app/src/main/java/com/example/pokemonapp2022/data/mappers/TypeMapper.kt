package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.data.dto.response.TypeResponse
import com.example.pokemonapp2022.domain.model.Type

fun TypeResponse.toType() : Type {
    return Type(slot = this.slot, type = this.type.toTypeDetails())
}