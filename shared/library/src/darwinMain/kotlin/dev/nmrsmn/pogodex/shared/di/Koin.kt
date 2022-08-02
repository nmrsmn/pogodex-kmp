package dev.nmrsmn.pogodex.shared.di

import dev.nmrsmn.pogodex.shared.Greeting
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GreetingHelper: KoinComponent {
    private val greeting : Greeting by inject()
    suspend fun greet() : String = greeting.greeting()
}

fun initKoinDarwin(): KoinApplication = initKoin {}