package dev.nmrsmn.pogodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.whenStarted
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.model.Pokemon
import dev.nmrsmn.pogodex.shared.pokemon.pokedex.api.viewmodel.PokedexViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel: PokedexViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var text by remember { mutableStateOf("Waiting...") }
            val state by viewModel.state.collectAsState()

            val lifecycle = LocalLifecycleOwner.current.lifecycle

            LaunchedEffect(Unit) {
                lifecycle.whenStarted {
                    launch {
                        viewModel.event.collectLatest { event ->
                            text = "Event!"
                        }
                    }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text)
                Text("${state.pokemon.count()}")
                Button(onClick = { viewModel.execute(PokedexViewModel.Action.PokemonClicked(Pokemon(1, "Test"), "Caught")) }) {
                    Text("Reset!")
                }
            }
        }
    }
}
