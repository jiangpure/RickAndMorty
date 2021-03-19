package com.jpure.rickandmorty.ext

/**
 * 加载结果
 * @author Jp
 * @date 2021/3/10.
 */
sealed class RmLoadResult<out T> {
    data class Success<out T>(val value: T) : RmLoadResult<T>()

    data class Failure(val throwable: Throwable?) : RmLoadResult<Nothing>()
}

inline fun <reified T> RmLoadResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is RmLoadResult.Success) {
        success(value)
    }
}

inline fun <reified T> RmLoadResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is RmLoadResult.Failure) {
        failure(throwable)
    }
}
