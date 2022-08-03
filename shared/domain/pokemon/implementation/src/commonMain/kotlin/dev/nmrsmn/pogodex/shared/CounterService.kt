package dev.nmrsmn.pogodex.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class CounterService(coroutineScope: CoroutineScope) {

    private val counterStateFlow = MutableStateFlow(0)
    val counter: StateFlow<Int> = counterStateFlow.asStateFlow()

    init {
        coroutineScope.launch {
            while (true) {
                delay(2.seconds)
                counterStateFlow.emit(counter.value + 1)
            }
        }
    }
}