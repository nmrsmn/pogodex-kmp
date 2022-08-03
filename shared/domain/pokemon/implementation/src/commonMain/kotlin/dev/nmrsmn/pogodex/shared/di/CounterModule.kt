package dev.nmrsmn.pogodex.shared.di

import dev.nmrsmn.pogodex.shared.CounterService
import org.koin.core.module.Module
import org.koin.dsl.module

fun counterModule(): Module = module {
    single { CounterService(get()) }
}