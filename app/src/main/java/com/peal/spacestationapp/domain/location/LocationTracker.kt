package com.peal.spacestationapp.domain.location

import android.location.Location
import com.peal.spacestationapp.core.domain.util.Result
import com.peal.spacestationapp.core.domain.util.LocationError


/**
 * Created by Peal Mazumder on 31/1/25.
 */

interface LocationTracker {
    suspend fun getCurrentLocation(): Result<Location, LocationError>
}