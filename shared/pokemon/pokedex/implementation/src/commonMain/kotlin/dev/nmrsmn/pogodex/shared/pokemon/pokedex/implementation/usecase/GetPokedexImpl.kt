package dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.usecase

import dev.nmrsmn.pogodex.shared.core.util.Result
import dev.nmrsmn.pogodex.shared.core.util.resultOf
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.model.Pokemon
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase.GetPokedex
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.domain.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetPokedexImpl(
    private val repository: PokemonRepository
) : GetPokedex {
    override suspend fun invoke(): Flow<Result<List<Pokemon>, Exception>> = repository.pokemon()
        .map {
            resultOf { it }
        }
        .catch {
            emit(Result.failure(IllegalStateException(it)))
        }
}