package dev.nmrsmn.pogodex.shared.di

import dev.nmrsmn.pogodex.shared.Greeting
import dev.nmrsmn.pogodex.shared.Platform
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun pokemonModule(): Module = module {
    single {
        Greeting(platform = Platform())
    }
}
