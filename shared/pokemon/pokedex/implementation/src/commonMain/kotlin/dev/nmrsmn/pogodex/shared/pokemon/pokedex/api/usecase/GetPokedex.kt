package dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase

import dev.nmrsmn.pogodex.shared.core.util.Result
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.model.Pokemon
import kotlinx.coroutines.flow.Flow

fun interface GetPokedex: suspend () -> Flow<Result<List<Pokemon>, Exception>>
