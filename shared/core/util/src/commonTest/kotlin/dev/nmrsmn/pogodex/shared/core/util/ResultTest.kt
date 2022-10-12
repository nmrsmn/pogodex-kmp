package dev.nmrsmn.pogodex.shared.core.util

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ResultTest {

    @Test
    fun `a 1`() {
        val actual = resultOf { "Test" }

        assertTrue(actual is Result.Success)
        assertEquals("Test", actual.value)
    }

    @Test
    fun `a 2`() {
        val actual = resultOf { throw IllegalStateException("Test") }

        assertTrue { actual is Result.Failure }
    }

    @Test
    fun `a 3`() {
        val actual = resultOf { "Test" }
            .map { it + it }

        assertTrue(actual is Result.Success)
        assertEquals("TestTest", actual.value)
    }

    @Test
    fun `a 4`() {
        val result: Result<String, Exception> = resultOf { throw IllegalStateException("Test") }
        val actual = result.map { it + it }

        assertTrue(actual is Result.Failure)
    }

    @Test
    fun `a 5`() {
        val expected = resultOf { "Test" }
        val actual = resultOf { "Test" }

        assertEquals(expected, actual)
    }

    @Test
    fun `a 6`() {
        val expected = resultOf { throw IllegalStateException("Test") }
        val actual = resultOf { throw IllegalStateException("Test") }

        assertEquals(expected, actual)
    }
}