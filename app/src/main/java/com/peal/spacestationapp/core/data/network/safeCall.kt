package com.peal.spacestationapp.core.data.network

import com.peal.spacestationapp.core.domain.util.NetworkError
import com.peal.spacestationapp.core.domain.util.Result
import kotlinx.coroutines.ensureActive
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    apiCall: () -> Response<T>
): Result<T, NetworkError> {
    val response = try {
        apiCall()
    } catch (e: IOException) {
        return Result.Failure(NetworkError.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Failure(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}

