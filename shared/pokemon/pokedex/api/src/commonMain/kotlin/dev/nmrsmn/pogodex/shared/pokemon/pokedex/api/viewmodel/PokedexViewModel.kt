package dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.viewmodel

import dev.nmrsmn.pogodex.shared.core.util.ViewModel
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.model.Pokemon

abstract class PokedexViewModel : ViewModel<PokedexViewModel.State, PokedexViewModel.Partial, PokedexViewModel.Action, Unit>(
    initial = State(true, emptyList())
) {
    data class State(
        val isLoading: Boolean,
        val pokemon: List<Pokemon>,
    )

    sealed class Partial {
        object Loading : Partial()
        class Success(val list: List<Pokemon>) : Partial()
        class Failed(val error: String) : Partial()
    }

    sealed class Action {
        object GetPokedex : Action()
        object RefreshPokedex : Action()
        class PokemonClicked(val pokemon: Pokemon, val collection: String) : Action()
    }
}