package com.peal.spacestationapp.domain.usecases

import android.location.Location
import com.peal.spacestationapp.core.domain.util.LocationError
import com.peal.spacestationapp.core.domain.util.Result
import com.peal.spacestationapp.domain.location.LocationTracker
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 31/1/25.
 */
class GetCurrentLocationUseCase @Inject constructor(
    private val locationTracker: LocationTracker,
) {
    suspend operator fun invoke(): Result<Location, LocationError> {
        return locationTracker.getCurrentLocation()
    }
}