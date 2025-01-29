package com.peal.spacestationapp.ui.login

import android.content.Context
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.peal.spacestationapp.Constants
import com.peal.spacestationapp.Constants.ERROR_TAG
import com.peal.spacestationapp.R
import kotlinx.coroutines.launch


/**
 * Created by Peal Mazumder on 30/1/25.
 */

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    logInState: LogInState,
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigation: (LoginNavigationEvent) -> Unit
) {
    LaunchedEffect(key1 = logInState.isSignInSuccessful) {
        if (logInState.isSignInSuccessful)
            onNavigation(LoginNavigationEvent.OnNavigateHome)
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
            onRequestResult = { credential ->
                viewModel.onIntent(LoginScreenIntent.OnSignInRequestResult(credential))
            }
        )
    }
}

@Composable
fun AuthenticationButton(
    text: String,
    @DrawableRes icon: Int,
    onRequestResult: (Credential) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .clickable {
                coroutineScope.launch {
                    launchCredManBottomSheet(
                        context, true, onRequestResult
                    )
                }
            }
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(vertical = 15.dp)
            .padding(start = 26.dp),
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
    }

}

suspend fun launchCredManBottomSheet(
    context: Context,
    hasFilter: Boolean = true,
    onRequestResult: (Credential) -> Unit
) {
    try {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(hasFilter)
            .setServerClientId(Constants.WEB_CLIENT_ID)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = CredentialManager.create(context).getCredential(
            request = request,
            context = context
        )

        onRequestResult(result.credential)
    } catch (e: NoCredentialException) {
        if (hasFilter)
            launchCredManBottomSheet(context, hasFilter = false, onRequestResult)
        Log.d(ERROR_TAG, e.message.orEmpty())
    } catch (e: GetCredentialException) {
        Log.d(ERROR_TAG, e.message.orEmpty())
    }
}