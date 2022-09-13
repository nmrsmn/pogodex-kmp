package dev.nmrsmn.pogodex.shared

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Suppress("MemberNameEqualsClassName")
class Greeting(private val platform: Platform) {
    suspend fun greeting(): String {
        delay(5.seconds)
        return "Hello, ${platform.name}"
    }
}
