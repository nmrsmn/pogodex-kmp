package dev.nmrsmn.pogodex.shared.core.database.di

import dev.nmrsmn.pogodex.database.PogedexCollector
import dev.nmrsmn.pogodex.shared.core.database.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun databaseModule(): Module = module {
    single { DatabaseDriverFactory().create() }
    single { PogedexCollector.invoke(get()) }
}