package com.peal.spacestationapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peal.spacestationapp.core.domain.util.onError
import com.peal.spacestationapp.core.domain.util.onSuccess
import com.peal.spacestationapp.domain.usecases.GetIssCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getIssCurrentLocationUseCase: GetIssCurrentLocationUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            getIssCurrentLocationUseCase.invoke().onSuccess {
                Log.d("ISS", "Success $it")
            }.onError {
                Log.d("ISS", "Failed $it")
            }

        }
    }
}