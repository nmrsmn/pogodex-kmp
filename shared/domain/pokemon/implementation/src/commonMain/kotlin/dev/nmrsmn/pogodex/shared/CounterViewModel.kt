package dev.nmrsmn.pogodex.shared

import dev.nmrsmn.pogodex.shared.core.util.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class CounterViewModel : ViewModel<CounterViewModel.Action, CounterViewModel.State>(initial = State()) {
    init {
        viewModelScope.launch {
            while (true) {
                delay(2.seconds)
                stateFlow.emit(stateFlow.value.copy(counter = stateFlow.value.counter + 1))
            }
        }
    }

    override fun Flow<Action>.process(): Flow<Action> = onEach {
        when (it) {
            Action.Reset -> stateFlow.emit(stateFlow.value.copy(counter =  0))
        }
    }

    data class State(val counter: Int = 0)

    sealed class Action {
        object Reset : Action()
    }
}


sealed class UserStatus<out T : Any> {
    class Success(val data: String) : UserStatus<String>()
    object NoAccount : UserStatus<Unit>()
    sealed class Error(val exception: Exception) : UserStatus<Nothing>() {
        class Generic(e: Exception) : Error(e)
        class Error1(e: Exception) : Error(e)
        class Error2(e: Exception) : Error(e)
        class Error3(e: Exception) : Error(e)
    }
}