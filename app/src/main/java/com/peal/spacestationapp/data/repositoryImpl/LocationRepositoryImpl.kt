package com.peal.spacestationapp.data.repositoryImpl

import android.content.Context
import android.location.Geocoder
import android.os.Build
import com.peal.spacestationapp.R
import com.peal.spacestationapp.domain.repository.LocationRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume


/**
 * Created by Peal Mazumder on 31/1/25.
 */

class LocationRepositoryImpl @Inject constructor(
    private val context: Context
) : LocationRepository {

    override suspend fun getCountryFromLatLng(lat: Double, lng: Double): Result<String> {
        return suspendCancellableCoroutine { continuation ->
            try {
                val geocoder = Geocoder(context.applicationContext, Locale.getDefault())

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    geocoder.getFromLocation(lat, lng, 1, object : Geocoder.GeocodeListener {
                        override fun onGeocode(addresses: MutableList<android.location.Address>) {
                            val country = addresses.firstOrNull()?.countryName ?: context.getString(
                                R.string.unknown
                            )
                            continuation.resume(Result.success(country))
                        }

                        override fun onError(errorMessage: String?) {
                            continuation.resume(
                                Result.failure(
                                    Exception(
                                        errorMessage ?: context.getString(
                                            R.string.geocoder_error
                                        )
                                    )
                                )
                            )
                        }
                    })
                } else {
                    @Suppress("DEPRECATION")
                    val addresses = geocoder.getFromLocation(lat, lng, 1)
                    val country = addresses?.firstOrNull()?.countryName ?: context.getString(
                        R.string.unknown
                    )
                    continuation.resume(Result.success(country))
                }
            } catch (e: Exception) {
                continuation.resume(Result.failure(e))
            }

            continuation.invokeOnCancellation {

            }
        }
    }
}