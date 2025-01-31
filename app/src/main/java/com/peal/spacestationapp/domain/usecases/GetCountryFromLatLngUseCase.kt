package com.peal.spacestationapp.domain.usecases


import com.peal.spacestationapp.domain.repository.LocationRepository
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 31/1/25.
 */

class GetCountryFromLatLngUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(lat: Double, lng: Double): Result<String> {
        return locationRepository.getCountryFromLatLng(lat, lng)
    }
}