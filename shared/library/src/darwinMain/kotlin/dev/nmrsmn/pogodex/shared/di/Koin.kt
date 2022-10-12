package dev.nmrsmn.pogodex.shared.di

import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.viewmodel.PokedexViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ViewModelProvider: KoinComponent {
    val pokemon: PokedexViewModel by inject()
}
