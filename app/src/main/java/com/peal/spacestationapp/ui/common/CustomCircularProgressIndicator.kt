package com.peal.spacestationapp.ui.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * Created by Peal Mazumder on 31/1/25.
 */


@Composable
fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 2.dp
) {
    CircularProgressIndicator(
        modifier = modifier,
        strokeWidth = strokeWidth,
        color = MaterialTheme.colorScheme.onBackground
    )
}