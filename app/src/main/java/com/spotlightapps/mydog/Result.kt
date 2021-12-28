package com.spotlightapps.mydog

import java.lang.Exception

/**
 * Created by Ahmad Jawid Muhammadi
 * on 28-12-2021.
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success [data = $data]"
            is Error -> "Error [error $exception"
            Loading -> "Loading"
        }
    }
}

val Result<*>.succeeded
get() = this is Result.Success && data != null
