package com.spotlightapps.mydog.domain

import com.spotlightapps.mydog.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Ahmad Jawid Muhammadi
 * on 31-12-2021.
 */

abstract class UseCase<in P, out R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    abstract suspend fun execute(parameters: P): R
}