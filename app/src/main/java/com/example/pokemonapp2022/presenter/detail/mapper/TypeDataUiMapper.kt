package com.example.pokemonapp2022.presenter.detail.mapper

import com.example.pokemonapp2022.domain.model.Type
import com.example.pokemonapp2022.presenter.detail.dataui.TypeDataUi

fun Type.toTypeDataUi() : TypeDataUi {
    return TypeDataUi(slot = this.slot, type = this.type.toTypeDetailsDataUi())
}