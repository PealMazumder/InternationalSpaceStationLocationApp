package com.peal.spacestationapp.core.data.network

import com.peal.spacestationapp.core.domain.util.NetworkError
import com.peal.spacestationapp.core.domain.util.Result
import retrofit2.Response


inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T, NetworkError> {
    return when {
        response.isSuccessful -> {
            val body = response.body()
            if (body != null) {
                Result.Success(body)
            } else {
                Result.Failure(NetworkError.SERIALIZATION)
            }
        }

        response.code() == 408 -> Result.Failure(NetworkError.REQUEST_TIMEOUT)
        response.code() == 429 -> Result.Failure(NetworkError.TOO_MANY_REQUESTS)
        response.code() in 500..599 -> Result.Failure(NetworkError.SERVER_ERROR)
        else -> Result.Failure(NetworkError.UNKNOWN)
    }
}