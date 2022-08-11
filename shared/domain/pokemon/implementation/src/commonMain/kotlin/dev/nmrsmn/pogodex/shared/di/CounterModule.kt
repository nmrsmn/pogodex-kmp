package dev.nmrsmn.pogodex.shared.di

import dev.nmrsmn.pogodex.shared.CounterViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun counterModule(): Module = module {
    single { CounterViewModel() }
}