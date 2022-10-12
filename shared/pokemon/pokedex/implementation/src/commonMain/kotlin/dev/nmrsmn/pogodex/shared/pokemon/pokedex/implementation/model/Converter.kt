package dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.model

import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.model.Pokemon
import dev.nmrsmn.pogodex.database.Pokemon as Entity

fun Entity.convert(): Pokemon = Pokemon(
    number = number,
    name = name
)