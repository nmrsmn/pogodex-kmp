package dev.nmrsmn.pogodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import dev.nmrsmn.pogodex.shared.Greeting
import dev.nmrsmn.pogodex.shared.Platform

class MainActivity : ComponentActivity() {

    private val greeting: Greeting = Greeting(Platform())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Text(greeting.greeting())
        }
    }
}