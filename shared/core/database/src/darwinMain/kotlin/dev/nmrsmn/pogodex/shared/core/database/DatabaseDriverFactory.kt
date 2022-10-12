package dev.nmrsmn.pogodex.shared.core.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import dev.nmrsmn.pogodex.database.PogedexCollector

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(PogedexCollector.Schema, "pogedexcollector.db")
    }
}