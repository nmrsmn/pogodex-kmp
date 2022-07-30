package dev.nmrsmn.pogodex.shared

object Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}"
    }
}