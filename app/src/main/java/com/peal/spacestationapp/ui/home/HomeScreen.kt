package com.peal.spacestationapp.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.peal.spacestationapp.R
import com.peal.spacestationapp.ui.common.CustomCircularProgressIndicator
import com.peal.spacestationapp.ui.home.components.IssDetailsCard


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeState: HomeScreenState,
    onIntent: (HomeIntent) -> Unit,
) {
    val context = LocalContext.current
    var isToastShown by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = homeState.isAboveCountry) {
        if (!isToastShown) {
            Toast.makeText(
                context,
                context.getString(R.string.the_space_station_is_above_your_country_now),
                Toast.LENGTH_SHORT
            ).show()
        }
        isToastShown = true
    }

    Card(
        modifier = modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IssDetailsCard(
                    homeState,
                    onIntent = {
                        onIntent(it)
                    }
                )
            }

            if (homeState.isLoading) {
                CustomCircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(34.dp),
                    strokeWidth = 3.dp
                )
            }
        }
    }
}