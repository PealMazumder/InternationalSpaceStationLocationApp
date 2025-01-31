package com.peal.spacestationapp.ui.home


/**
 * Created by Peal Mazumder on 31/1/25.
 */

sealed class HomeEvent {
    data object RefreshClick : HomeEvent()
}