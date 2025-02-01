package com.peal.spacestationapp.core.data.network

import com.peal.spacestationapp.core.domain.util.NetworkError
import com.peal.spacestationapp.core.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

suspend inline fun <reified T> safeCall(
    crossinline apiCall: suspend () -> Response<T>
): Result<T, NetworkError> {
    return withContext(Dispatchers.IO) {
        val response = try {
            apiCall()
        } catch (e: IOException) {
            return@withContext Result.Failure(NetworkError.NO_INTERNET)
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            return@withContext Result.Failure(NetworkError.UNKNOWN)
        }
        responseToResult(response)
    }
}


