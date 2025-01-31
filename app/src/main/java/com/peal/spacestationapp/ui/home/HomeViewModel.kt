package com.peal.spacestationapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peal.spacestationapp.core.domain.util.onError
import com.peal.spacestationapp.core.domain.util.onSuccess
import com.peal.spacestationapp.domain.usecases.CheckIsIssAboveUserUseCase
import com.peal.spacestationapp.domain.usecases.GetCountryFromLatLngUseCase
import com.peal.spacestationapp.domain.usecases.GetCurrentLocationUseCase
import com.peal.spacestationapp.domain.usecases.GetIssCurrentLocationUseCase
import com.peal.spacestationapp.ui.home.model.toIssLocationInfoUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getIssCurrentLocationUseCase: GetIssCurrentLocationUseCase,
    private val getCountryFromLatLngUseCase: GetCountryFromLatLngUseCase,
    private val checkIsIssAboveUserUseCase: CheckIsIssAboveUserUseCase,
    private val locationUseCase: GetCurrentLocationUseCase,

    ) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeScreenState())
    val homeState: StateFlow<HomeScreenState> = _homeState
    private var userLatitude: Double = 0.0
    private var userLongitude: Double = 0.0

    private var countdownJob: Job? = null

    init {
        onFetchCurrentLocation()
        fetchISSLocation()
    }

    private fun onFetchCurrentLocation() {
        viewModelScope.launch {
            locationUseCase.invoke().onSuccess { location ->
                userLatitude = location.latitude
                userLongitude = location.longitude
                Log.d("ISS", "userLocation: $userLatitude, $userLongitude")
            }.onError {

            }
        }
    }

    private fun fetchISSLocation() {
        viewModelScope.launch {
            _homeState.update { it.copy(isLoading = true) }
            getIssCurrentLocationUseCase.invoke().onSuccess { issLocInfo ->
                getCountryFromLatLngUseCase.invoke(issLocInfo.latitude, issLocInfo.longitude)
                    .onSuccess { country ->

                        _homeState.update {
                            it.copy(
                                isLoading = false,
                                issLocationInfo = issLocInfo.toIssLocationInfoUi(country),
                                isAboveCountry = checkIsIssAboveUserUseCase.invoke(
                                    issLocInfo.latitude,
                                    issLocInfo.longitude,
                                    userLatitude,
                                    userLongitude
                                )
                            )
                        }
                        startCountdown()
                    }
                    .onFailure { error ->
                        Log.d("ISS", "Failed to get country: $error")
                        _homeState.update { it.copy(isLoading = false) }
                        startCountdown()
                    }
            }.onError { error ->
                Log.d("ISS", "Failed to get ISS location: $error")
                _homeState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            for (countDownTime in 10 downTo 0) {
                _homeState.update { it.copy(countDownTime = countDownTime.toString()) }
                if (countDownTime != 0)
                    delay(1000)
                else {
                    delay(500)
                    _homeState.update { it.copy(countDownTime = "") }
                }

            }
            fetchISSLocation()
        }
    }

    fun handleHomeEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.RefreshClick -> onRefreshClick()
        }
    }

    private fun onRefreshClick() {
        countdownJob?.cancel()
        fetchISSLocation()
    }
}