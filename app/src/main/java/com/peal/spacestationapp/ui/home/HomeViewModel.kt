package com.peal.spacestationapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peal.spacestationapp.core.domain.util.onError
import com.peal.spacestationapp.core.domain.util.onSuccess
import com.peal.spacestationapp.domain.usecases.GetIssCurrentLocationUseCase
import com.peal.spacestationapp.ui.home.model.toIssLocationInfoUi
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        viewModelScope.launch {
            getIssCurrentLocationUseCase.invoke().onSuccess { issLocInfo ->
                _homeState.update {
                    it.copy(
                        isLoading = false,
                        issLocationInfo = issLocInfo.toIssLocationInfoUi()
                    )
                }
            }.onError {
                Log.d("ISS", "Failed $it")
            }

        }
    }
}