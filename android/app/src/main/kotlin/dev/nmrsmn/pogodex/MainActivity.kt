package dev.nmrsmn.pogodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import dev.nmrsmn.pogodex.shared.CounterService
import dev.nmrsmn.pogodex.shared.Greeting
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val greeting: Greeting by inject()
    private val counter: CounterService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var text by remember { mutableStateOf("Waiting...") }
            val counter by counter.counter.collectAsState()

            LaunchedEffect(key1 = Unit) {
                text = greeting.greeting()
            }

            Column {
                Text(text)
                Text("$counter")
            }
        }
    }
}