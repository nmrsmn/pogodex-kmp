package dev.nmrsmn.pogodex.shared.core.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dev.nmrsmn.pogodex.database.PogedexCollector

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun create(): SqlDriver
        = AndroidSqliteDriver(PogedexCollector.Schema, context, "pogedexcollector.db")
}