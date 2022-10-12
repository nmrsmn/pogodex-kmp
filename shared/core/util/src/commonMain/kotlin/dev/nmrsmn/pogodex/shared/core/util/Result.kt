package dev.nmrsmn.pogodex.shared.core.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException

sealed class Result<out V, out E> {

    data class Success<out V> internal constructor(val value: V) : Result<V, Nothing>()
    data class Failure<out E> internal constructor(val error: E) : Result<Nothing, E>()

    companion object {

        fun <V> success(value: V): Success<V> = Success(value)
        fun <E> failure(error: E): Failure<E> = Failure(error)
    }
}

inline fun <R, E> resultOf(block: () -> R): Result<R, Exception> = try {
    Result.success(block())
} catch (exception: TimeoutCancellationException) {
    Result.failure(exception)
} catch (exception: CancellationException) {
    throw exception
} catch (exception: Exception) {
    Result.failure(exception)
}

inline fun <T, R> T.resultOf(block: T.() -> R): Result<R, Exception> = try {
    Result.success(block())
} catch (exception: TimeoutCancellationException) {
    Result.failure(exception)
} catch (exception: CancellationException) {
    throw exception
} catch (exception: Exception) {
    Result.failure(exception)
}

inline fun <R, T> Result<T, Exception>.map(transform: (value: T) -> R): Result<R, Exception> = when (this) {
    is Result.Success -> resultOf { transform(value) }
    is Result.Failure -> this
}