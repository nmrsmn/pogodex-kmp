package dev.nmrsmn.pogodex.shared.core.util

import kotlinx.coroutines.cancel

@Suppress("MemberNameEqualsClassName")
actual open class ViewModelScope actual constructor() {
    protected actual val viewModelScope = createViewModelScope()

    actual open fun onCleared() {
        viewModelScope.cancel()
    }
}
