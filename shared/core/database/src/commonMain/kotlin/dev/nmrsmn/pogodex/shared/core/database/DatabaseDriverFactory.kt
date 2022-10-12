package dev.nmrsmn.pogodex.shared.core.database

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}