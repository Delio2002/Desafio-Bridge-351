package com.example.pokemonapp2022.presenter.detail.mapper

import com.example.pokemonapp2022.domain.model.Ability
import com.example.pokemonapp2022.presenter.detail.dataui.AbilityDataUi


fun Ability.ToAbilityDataUi() :AbilityDataUi{
    return AbilityDataUi(ability = this.ability.toAbilityDetailsDataUi(), is_hidden = this.is_hidden, slot = this.slot)
}