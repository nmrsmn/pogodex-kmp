package dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase

import dev.nmrsmn.pogodex.shared.core.util.Result

fun interface RefreshPokedex: suspend () -> Result<Unit, Exception>
