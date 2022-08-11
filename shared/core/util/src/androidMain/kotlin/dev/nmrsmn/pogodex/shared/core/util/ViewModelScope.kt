package dev.nmrsmn.pogodex.shared.core.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.cancel

actual open class ViewModelScope actual constructor() : ViewModel() {
    protected actual val viewModelScope = createViewModelScope()

    public actual override fun onCleared() {
        super.onCleared()

        viewModelScope.cancel()
    }
}