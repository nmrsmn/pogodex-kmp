package dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.data

import dev.nmrsmn.pogodex.database.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {
    suspend fun getPokemon(): Flow<List<Pokemon>>
}