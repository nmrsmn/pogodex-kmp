package dev.nmrsmn.pogodex.shared

import dev.nmrsmn.pogodex.shared.core.util.commonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class CounterService(coroutineScope: CoroutineScope) {

    private val counterStateFlow = MutableStateFlow(0)
    val counter by commonStateFlow(counterStateFlow)

    init {
        coroutineScope.launch {
            while (true) {
                delay(1.seconds)
                counterStateFlow.emit(counterStateFlow.value + 1)
            }
        }
    }
}