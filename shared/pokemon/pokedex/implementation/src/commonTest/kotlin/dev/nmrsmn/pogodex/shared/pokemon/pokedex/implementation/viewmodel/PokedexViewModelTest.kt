package dev.nmrsmn.pogodex.shared.pokemon.pokedex.implementation.viewmodel

import dev.nmrsmn.pogodex.shared.core.util.Result
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase.GetPokedex
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.usecase.RefreshPokedex
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.viewmodel.PokedexViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlin.test.Test
import kotlin.test.assertEquals

class PokedexViewModelTest {

    @Test
    fun `a`() {
        val getPokedexUseCase = mockk<GetPokedex>()
        val refreshPokedexUseCase = mockk<RefreshPokedex>()

        coEvery { getPokedexUseCase.invoke() } returns flowOf(Result.failure(IllegalStateException("Test")))

        val viewModel = PokedexViewModelImpl(getPokedexUseCase, refreshPokedexUseCase)

        viewModel.execute(PokedexViewModel.Action.GetPokedex)

        assertEquals(true, viewModel.state.value.isLoading)
    }
}