package dev.nmrsmn.pogodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import dev.nmrsmn.pogodex.shared.Greeting
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val greeting: Greeting by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Text(greeting.greeting())
        }
    }
}