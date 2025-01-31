package com.peal.spacestationapp.ui.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peal.spacestationapp.R
import com.peal.spacestationapp.domain.model.AuthenticationType
import com.peal.spacestationapp.ui.common.CustomCircularProgressIndicator


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    logInState: LogInState,
    onIntent: (LoginScreenIntent) -> Unit,
    onNavigation: (LoginNavigationEvent) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = logInState.isSignInSuccessful) {
        if (logInState.isSignInSuccessful) {
            onNavigation(LoginNavigationEvent.OnNavigateHome)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        AuthenticationButton(
            text = stringResource(R.string.sign_in_with_google),
            icon = R.drawable.ic_google,
            logInState.isLoginInProgress
        ) {
            onIntent(LoginScreenIntent.OnSignInRequest(AuthenticationType.Google, context))
        }
    }
}

@Composable
fun AuthenticationButton(
    text: String,
    @DrawableRes icon: Int,
    isLoginInProgress: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable {
                if (!isLoginInProgress) {
                    onClick.invoke()
                }
            }
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(vertical = 15.dp)
            .padding(start = 26.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "OAuth Icon",
            tint = Color.Unspecified,
            modifier = Modifier
                .width(31.dp)
                .height(31.dp)
        )

        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 12.dp)
        )

        if (isLoginInProgress) {
            Spacer(modifier = Modifier.weight(1f))
            CustomCircularProgressIndicator(
                modifier = Modifier.size(30.dp),
                strokeWidth = 2.dp
            )
        }
    }

}