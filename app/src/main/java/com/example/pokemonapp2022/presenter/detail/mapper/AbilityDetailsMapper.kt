package com.example.pokemonapp2022.presenter.detail.mapper

import com.example.pokemonapp2022.domain.model.AbilityDetails
import com.example.pokemonapp2022.presenter.detail.dataui.AbilityDetailsDataUi

fun AbilityDetails.toAbilityDetailsDataUi() : AbilityDetailsDataUi{
    return AbilityDetailsDataUi(name = this.name, url = this.url)
}