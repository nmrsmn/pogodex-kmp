package dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.domain

import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun pokemon(): Flow<List<Pokemon>>
    suspend fun refresh(): Unit
}