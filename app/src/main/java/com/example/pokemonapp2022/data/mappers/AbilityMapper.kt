package com.example.pokemonapp2022.data.mappers

import com.example.pokemonapp2022.data.dto.response.AbilityResponse
import com.example.pokemonapp2022.domain.model.Ability


fun AbilityResponse.toAbility() :Ability{
    return Ability(ability = this.ability.toAbilityDetails(), is_hidden = this.is_hidden, slot = this.slot)
}