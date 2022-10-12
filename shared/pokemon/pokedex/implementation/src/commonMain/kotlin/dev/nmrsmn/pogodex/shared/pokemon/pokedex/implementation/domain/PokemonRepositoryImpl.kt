package dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.domain

import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.domain.PokemonRepository
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.model.Pokemon
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.data.LocalPokemonDataSource
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.model.convert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class PokemonRepositoryImpl(
    private val localPokemonDataSource: LocalPokemonDataSource
): PokemonRepository {
    override suspend fun pokemon(): Flow<List<Pokemon>> = localPokemonDataSource
        .getPokemon()
        .map { items ->
            items.map { item -> item.convert() }
        }
        .onEach { items ->
            if (items.isEmpty()) {
                refresh()
            }
        }

    override suspend fun refresh() {
        // Refresh the pokemon from API
    }
}
