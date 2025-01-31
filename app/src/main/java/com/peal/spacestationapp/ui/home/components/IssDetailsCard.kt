package com.peal.spacestationapp.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.peal.spacestationapp.R
import com.peal.spacestationapp.ui.home.HomeEvent
import com.peal.spacestationapp.ui.home.HomeScreenState


@Composable
fun IssDetailsCard(
    homeScreenState: HomeScreenState,
    onEvent: (HomeEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = homeScreenState.issLocationInfo.country,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        CustomHorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            height = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
        )

        Text(
            text = stringResource(
                R.string.latitude_longitude,
                homeScreenState.issLocationInfo.latitude,
                homeScreenState.issLocationInfo.longitude
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = stringResource(R.string.refresh_countdown),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )


        Text(
            text = homeScreenState.countDownTime,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )

        Button(
            onClick = { onEvent(HomeEvent.RefreshClick) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = stringResource(R.string.refresh),
                style = MaterialTheme.typography.labelLarge
            )
        }

        Text(
            text = stringResource(R.string.local_time, homeScreenState.issLocationInfo.localTime),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = stringResource(R.string.utc_time, homeScreenState.issLocationInfo.utcTime),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}