package com.peal.spacestationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.peal.spacestationapp.ui.navigation.NavGraph
import com.peal.spacestationapp.ui.theme.SpaceStationAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpaceStationAppTheme {
                val isAuthenticated by rememberSaveable { mutableStateOf(Firebase.auth.currentUser != null) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding),
                        isAuthenticated = isAuthenticated
                    )
                }
            }
        }
    }
}
