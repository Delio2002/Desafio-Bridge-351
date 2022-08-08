package com.example.pokemonapp2022.domain.model



data class Pokemon(
    val abilities: List<Ability>,
    val base_experience: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val photo: String,
    val weight: Int,
    val types: List<Type>,
)