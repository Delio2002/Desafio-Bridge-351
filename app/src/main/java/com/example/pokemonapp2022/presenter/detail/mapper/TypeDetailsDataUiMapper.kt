package com.example.pokemonapp2022.presenter.detail.mapper

import com.example.pokemonapp2022.data.dto.response.TypeXResponse
import com.example.pokemonapp2022.domain.model.TypeDetails
import com.example.pokemonapp2022.presenter.detail.dataui.TypeDetailsDataUi

fun TypeDetails.toTypeDetailsDataUi() : TypeDetailsDataUi {
    return TypeDetailsDataUi(name = this.name, url = this.url)
}