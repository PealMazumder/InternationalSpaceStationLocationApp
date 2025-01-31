package com.peal.spacestationapp.ui.home

import androidx.compose.runtime.Immutable
import com.peal.spacestationapp.ui.home.model.IssLocationInfoUi


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Immutable
data class HomeScreenState(
    val isLoading: Boolean = false,
    val issLocationInfo: IssLocationInfoUi = IssLocationInfoUi(),
    val countDownTime: String = "",
    val isAboveCountry: Boolean = false
)
