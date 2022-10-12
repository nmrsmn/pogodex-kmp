package dev.nmrsmn.pogodex.shared.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(Dispatchers.Main + SupervisorJob())
}

@Suppress("EmptyDefaultConstructor", "MemberNameEqualsClassName")
expect open class ViewModelScope() {
    protected val viewModelScope: CoroutineScope

    open fun onCleared()
}

abstract class ViewModel<State, Partial, Action, Event>(initial: State): ViewModelScope() {
    @Suppress("MemberVisibilityCanBePrivate")
    private val actions = MutableSharedFlow<Action>()

    @Suppress("MemberVisibilityCanBePrivate")
    protected val stateFlow = MutableStateFlow(initial)
    val state: CommonStateFlow<State> by commonStateFlow(stateFlow)

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val event: CommonFlow<Event> by commonFlow(eventChannel.receiveAsFlow())

    init {
        viewModelScope.launch {
            actions
                .flatMapMerge { action -> process(action) }
                .scan(initial) { accumulator, value -> reduce(accumulator, value) }
                .collect { state -> stateFlow.emit(state) }
        }
    }

    fun execute(action: Action) = viewModelScope.launch {
        actions.emit(action)
    }

    protected fun publish(event: Event) = viewModelScope.launch {
        eventChannel.send(event)
    }

    protected abstract fun process(action: Action): Flow<Partial>
    protected abstract fun reduce(previous: State, partial: Partial): State
}
