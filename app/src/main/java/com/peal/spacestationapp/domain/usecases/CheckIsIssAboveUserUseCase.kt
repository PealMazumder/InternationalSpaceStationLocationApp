package com.peal.spacestationapp.domain.usecases

import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Created by Peal Mazumder on 31/1/25.
 */

class CheckIsIssAboveUserUseCase @Inject constructor() {
    operator fun invoke(
        issLatitude: Double,
        issLongitude: Double,
        userLatitude: Double,
        userLongitude: Double,
    ): Boolean {
        return isIssAboveLocation(
            issLat = issLatitude,
            issLong = issLongitude,
            userLat = userLatitude,
            userLong = userLongitude,
        )
    }

    private fun isIssAboveLocation(
        issLat: Double,
        issLong: Double,
        userLat: Double,
        userLong: Double,
        radiusKm: Double = 500.0
    ): Boolean {
        val distance = calculateDistance(issLat, issLong, userLat, userLong)
        return distance <= radiusKm
    }

    private fun calculateDistance(
        lat1: Double, lon1: Double,
        lat2: Double, lon2: Double
    ): Double {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat/2) * sin(dLat/2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon/2) * sin(dLon/2)
        val c = 2 * atan2(sqrt(a), sqrt(1-a))
        return 6371 * c
    }
}