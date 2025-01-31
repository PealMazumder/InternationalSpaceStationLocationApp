package com.peal.spacestationapp.domain.repository



/**
 * Created by Peal Mazumder on 31/1/25.
 */

interface LocationRepository {
    suspend fun getCountryFromLatLng(lat: Double, lng: Double): Result<String>
}