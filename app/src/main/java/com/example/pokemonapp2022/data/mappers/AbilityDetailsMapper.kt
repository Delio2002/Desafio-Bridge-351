package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.data.dto.response.AbilityXResponse
import com.example.pokemonapp2022.domain.model.AbilityDetails

fun AbilityXResponse.toAbilityDetails() : AbilityDetails{
    return AbilityDetails(name = this.name, url = this.url)
}