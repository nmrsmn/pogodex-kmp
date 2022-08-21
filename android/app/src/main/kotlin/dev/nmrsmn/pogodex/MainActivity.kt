package dev.nmrsmn.pogodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import dev.nmrsmn.pogodex.shared.CounterViewModel
import dev.nmrsmn.pogodex.shared.Greeting
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val greeting: Greeting by inject()
    private val viewModel: CounterViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var text by remember { mutableStateOf("Waiting...") }
            val state by viewModel.state.collectAsState()

            LaunchedEffect(Unit) {
                text = greeting.greeting()
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text)
                Text("${state.counter}")
                Button(onClick = { viewModel.actions.trySend(CounterViewModel.Action.Reset) }) {
                    Text("Reset!")
                }
            }
        }
    }
}