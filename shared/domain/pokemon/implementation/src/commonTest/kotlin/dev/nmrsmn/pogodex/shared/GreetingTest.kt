package dev.nmrsmn.pogodex.shared

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GreetingTest {

    @Test
    fun `Given a platform instance greeting will return the correct value`() = runBlocking {
        val name = "Anything"

        val platform = mockk<Platform>()
        val service = Greeting(platform)

        every { platform.name } returns name

        assertEquals("Hello, $name", service.greeting())

        verify(exactly = 1) { platform.name }
    }
}