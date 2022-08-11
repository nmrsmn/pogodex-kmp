package dev.nmrsmn.pogodex.shared.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(Dispatchers.Main + SupervisorJob())
}

@Suppress("EmptyDefaultConstructor")
expect open class ViewModelScope() {
    protected val viewModelScope: CoroutineScope

    open fun onCleared()
}

abstract class ViewModel<Action, State>(initial: State): ViewModelScope() {

    @Suppress("MemberVisibilityCanBePrivate")
    val actions = Channel<Action>()
    private val actionProcessingFlow by lazy { actions.consumeAsFlow().process() }

    @Suppress("MemberVisibilityCanBePrivate")
    protected val stateFlow = MutableStateFlow(initial)
    val state: CommonStateFlow<State> by commonStateFlow(stateFlow)

    init {
        actionProcessingFlow.launchIn(viewModelScope)
    }

    protected abstract fun Flow<Action>.process(): Flow<Action>
}