package dev.nmrsmn.pogodex.shared

class Greeting(private val platform: Platform) {
    fun greeting(): String {
        return "Hello, ${platform.name}"
    }
}