package com.example.labactivity10.core

sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>

    sealed interface Failure : AppResult<Nothing> {
        data object NoInternet : Failure
        data object Timeout : Failure
        data class Unknown(val message: String?) : Failure
    }
}
