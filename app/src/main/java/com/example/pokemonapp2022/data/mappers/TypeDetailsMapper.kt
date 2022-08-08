package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.data.dto.response.TypeXResponse
import com.example.pokemonapp2022.domain.model.TypeDetails

fun TypeXResponse.toTypeDetails() : TypeDetails {
    return TypeDetails(name = this.name, url = this.url)
}