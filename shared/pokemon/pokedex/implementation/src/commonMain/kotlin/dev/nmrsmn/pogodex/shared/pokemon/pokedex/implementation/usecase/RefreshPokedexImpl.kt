package dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.usecase

import dev.nmrsmn.pogodex.shared.core.util.Result
import dev.nmrsmn.pogodex.shared.core.util.resultOf
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.domain.PokemonRepository
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase.RefreshPokedex

class RefreshPokedexImpl(
    private val repository: PokemonRepository
) : RefreshPokedex {
    override suspend fun invoke(): Result<Unit, Exception> = resultOf {
        repository.refresh()
    }
}