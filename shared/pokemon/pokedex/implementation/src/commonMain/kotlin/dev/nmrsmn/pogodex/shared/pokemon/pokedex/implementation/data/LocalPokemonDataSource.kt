package dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.data

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import dev.nmrsmn.pogodex.database.PogedexCollector
import dev.nmrsmn.pogodex.database.Pokemon
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.data.PokemonDataSource
import kotlinx.coroutines.flow.Flow

class LocalPokemonDataSource(
    private val database: PogedexCollector
): PokemonDataSource {
    override suspend fun getPokemon(): Flow<List<Pokemon>> = database.pogodexCollectorQueries
        .selectPokemon()
        .asFlow()
        .mapToList()
}
