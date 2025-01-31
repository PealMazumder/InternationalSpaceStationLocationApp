package com.peal.spacestationapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.peal.spacestationapp.ui.home.components.IssDetailsCard


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Composable
fun HomeScreen(
    modifier: Modifier,
    homeState: HomeScreenState,
    onEvent: (HomeEvent) -> Unit,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IssDetailsCard(
                    homeState,
                    onEvent = {
                        onEvent(it)
                    }
                )
            }
        }
    }
}