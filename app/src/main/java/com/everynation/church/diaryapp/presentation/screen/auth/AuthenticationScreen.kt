package com.everynation.church.diaryapp.presentation.screen.auth

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.everynation.church.diaryapp.onetapsingin.OneTapSignInState
import com.everynation.church.diaryapp.onetapsingin.OneTapSignInWithGoogle
import com.everynation.church.diaryapp.util.Constants

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    oneTapSignInState: OneTapSignInState,
    isAuthenticated: Boolean,
    loadingState: Boolean,
    onButtonClicked: () -> Unit,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit,
    navigateToHome: () -> Unit,

    ) {
    Scaffold(
        modifier = modifier,
        content = {
            it
            AuthenticationContent(
                loadingState = loadingState,
                onButtonClicked = onButtonClicked
            )
        }
    )
    
    OneTapSignInWithGoogle(
        state = oneTapSignInState,
        clientId = Constants.CLIENT_ID,
        onTokenIdReceived = {
            Log.i("TAG", "AuthenticationScreen: $it")
            onSuccess(it)
        },
        onDialogDismissed = {
            Log.e("TAG", "AuthenticationScreen() called $it")
            onError(it)
        }
    )

    LaunchedEffect(key1 = isAuthenticated) {
        if (isAuthenticated) {
            navigateToHome()
        }
    }
}