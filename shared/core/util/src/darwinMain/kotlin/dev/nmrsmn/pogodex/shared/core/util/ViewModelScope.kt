package dev.nmrsmn.pogodex.shared.core.util

import kotlinx.coroutines.cancel

actual open class ViewModelScope actual constructor() {
    protected actual val viewModelScope = createViewModelScope()

    actual open fun onCleared() {
        viewModelScope.cancel()
    }
}