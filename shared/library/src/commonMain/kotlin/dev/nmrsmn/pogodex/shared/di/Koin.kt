package dev.nmrsmn.pogodex.shared.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        dispatcherModule,
        counterModule(),
        pokemonModule()
    )
}

fun initKoin() = initKoin {}

val dispatcherModule = module {
    factory { Dispatchers.Default }
    factory { MainScope() }
}