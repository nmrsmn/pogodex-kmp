package dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.di

import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.domain.PokemonRepository
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase.GetPokedex
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase.RefreshPokedex
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.viewmodel.PokedexViewModel
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.data.LocalPokemonDataSource
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.domain.PokemonRepositoryImpl
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.usecase.GetPokedexImpl
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.usecase.RefreshPokedexImpl
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.viewmodel.PokedexViewModelImpl
import org.koin.core.module.Module
import org.koin.dsl.module

fun pokemonModule(): Module = module {
    single { LocalPokemonDataSource(get()) }

    single<PokemonRepository> { PokemonRepositoryImpl(get()) }

    single<GetPokedex> { GetPokedexImpl(get()) }
    single<RefreshPokedex> { RefreshPokedexImpl(get()) }

    single<PokedexViewModel> { PokedexViewModelImpl(get(), get()) }
}