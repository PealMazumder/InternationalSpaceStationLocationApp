package com.peal.spacestationapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peal.spacestationapp.core.domain.util.onError
import com.peal.spacestationapp.core.domain.util.onSuccess
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
    private val getIssCurrentLocationUseCase: GetIssCurrentLocationUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeScreenState())
    val homeState: StateFlow<HomeScreenState> = _homeState

    private var countdownJob: Job? = null

    init {
        startCountdown()
        fetchISSLocation()
    }

    private fun fetchISSLocation() {
        viewModelScope.launch {
            getIssCurrentLocationUseCase.invoke().onSuccess { issLocInfo ->
                _homeState.update {
                    it.copy(
                        isLoading = false,
                        issLocationInfo = issLocInfo.toIssLocationInfoUi()
                    )
                }
                startCountdown()
            }.onError {
                Log.d("ISS", "Failed $it")
            }

        }
    }

    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            for (i in 60 downTo 0) {
                _homeState.update { it.copy(countDownTime = i.toString()) }
                delay(1000)
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