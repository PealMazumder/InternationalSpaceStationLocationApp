package com.peal.spacestationapp.core.domain.util


/**
 * Created by Peal Mazumder on 31/1/25.
 */

enum class LocationError: Error {
    PERMISSION_NOT_GRANTED,
    LOCATION_NOT_FOUND,
    LOCATION_FETCH_FAILED,
    GPS_DISABLED
}