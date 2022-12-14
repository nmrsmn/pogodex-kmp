package dev.nmrsmn.pogodex.shared.core.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty

interface ObservableFlow<T> {
    suspend fun watch(block: (T) -> Unit): Cancellable
    suspend fun collect(collector: CommonFlowCollector<T>)
}

class CommonFlow<T>(private val origin: Flow<T>): ObservableFlow<T>, Flow<T> by origin {
    override suspend fun watch(block: (T) -> Unit): Cancellable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        scope.launch {
            try {
                collect { value ->
                    block(value)
                }
            } catch (e: CancellationException) {
                println(e.message)
            }
        }

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }

    override suspend fun collect(collector: CommonFlowCollector<T>) {
        try {
            collect { value ->
                collector.emit(value)
            }
        } catch (exception: CancellationException) {
            collector.fail(exception)
        }
    }
}

class CommonStateFlow<T>(private val origin: StateFlow<T>): ObservableFlow<T>, StateFlow<T> by origin {
    override suspend fun watch(block: (T) -> Unit): Cancellable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        scope.launch {
            try {
                collect { value ->
                    block(value)
                }
            } catch (e: CancellationException) {
                println(e.message)
            }
        }

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }

    override suspend fun collect(collector: CommonFlowCollector<T>) {
        try {
            collect { value ->
                collector.emit(value)
            }
        } catch (exception: CancellationException) {
            collector.fail(exception)
        }
    }
}

class CommonSharedFlow<T>(private val origin: SharedFlow<T>): ObservableFlow<T>, SharedFlow<T> by origin {
    override suspend fun watch(block: (T) -> Unit): Cancellable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        scope.launch {
            try {
                collect { value ->
                    block(value)
                }
            } catch (e: CancellationException) {
                println(e.message)
            }
        }

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }

    override suspend fun collect(collector: CommonFlowCollector<T>) {
        try {
            collect { value ->
                collector.emit(value)
            }
        } catch (exception: CancellationException) {
            collector.fail(exception)
        }
    }
}

interface CommonFlowCollector<T> : FlowCollector<T> {
    suspend fun fail(exception: Exception)
    override suspend fun emit(value: T)
}

interface Cancellable {
    fun cancel()
}

inline fun <T> commonFlow(flow: Flow<T>) : ReadOnlyProperty<Any?, CommonFlow<T>>
    = ReadOnlyProperty { _, _ -> CommonFlow(flow) }

inline fun <T> commonStateFlow(flow: StateFlow<T>) : ReadOnlyProperty<Any?, CommonStateFlow<T>>
    = ReadOnlyProperty { _, _ -> CommonStateFlow(flow) }

inline fun <T> commonSharedFlow(flow: SharedFlow<T>) : ReadOnlyProperty<Any?, CommonSharedFlow<T>>
    = ReadOnlyProperty { _, _ -> CommonSharedFlow(flow) }
