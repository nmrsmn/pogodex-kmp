package dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.viewmodel

import dev.nmrsmn.pogodex.shared.core.util.Result
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase.GetPokedex
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase.RefreshPokedex
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.viewmodel.PokedexViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart

class PokedexViewModelImpl(
    private val getPokedexUseCase: GetPokedex,
    private val refreshPokedexUseCase: RefreshPokedex
) : PokedexViewModel() {

    override fun process(action: Action): Flow<Partial> = when (action) {
        is Action.GetPokedex -> getPokedex()
        is Action.RefreshPokedex -> refreshPokedex()
        is Action.PokemonClicked -> flowOf(Partial.Failed("Something went wrong"))
    }

    override fun reduce(previous: State, partial: Partial): State = when(partial) {
        is Partial.Loading -> previous.copy(isLoading = true)
        is Partial.Success -> previous.copy(isLoading = false, pokemon = partial.list)
        is Partial.Failed -> previous.also {
            publish(Unit) // TODO: Implement event to log error or add boolean to state.
        }
    }

    private fun getPokedex() = flow {
        getPokedexUseCase.invoke()
            .onStart {
                emit(Partial.Loading)
            }
            .collect { result ->
                when (result) {
                    is Result.Success -> emit(Partial.Success(result.value))
                    is Result.Failure -> emit(Partial.Failed(result.error.message ?: ""))
                }
            }
    }

    private fun refreshPokedex() = flow {
        when (val result = refreshPokedexUseCase.invoke()) {
            is Result.Failure -> emit(Partial.Failed(result.error.message ?: ""))
            else -> Unit
        }
    }
}