package com.peal.spacestationapp.ui.navigation
import kotlinx.serialization.Serializable
/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Serializable
sealed class Screens {
    @Serializable
    data object LoginScreen : Screens()

    @Serializable
    data object HomeScreen : Screens()
}
