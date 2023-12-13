package com.everynation.church.diaryapp.navigation

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.everynation.church.diaryapp.onetapsingin.rememberOneTapSignInState
import com.everynation.church.diaryapp.presentation.screen.auth.AuthViewModel
import com.everynation.church.diaryapp.presentation.screen.auth.AuthenticationScreen
import com.everynation.church.diaryapp.util.Constants
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.runBlocking

@Composable
fun SetUpNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        startDestination = startDestination, navController = navController
    ) {
        authenticationRoute {
            navController.popBackStack()
            navController.navigate(Screens.Home.route)
        }
        homeRoute()
        writeRoute()
    }
}

private fun NavGraphBuilder.writeRoute() {
    composable(
        Screens.Write.route,
        arguments = listOf(navArgument(name = Constants.WRITE_SCREEN_ARGUMENT_KEY) {
            type = NavType.StringType
            defaultValue = null
            nullable = true
        })
    ) {

    }
}

private fun NavGraphBuilder.homeRoute() {
    composable(Screens.Home.route) {

    }
}

fun NavGraphBuilder.authenticationRoute(navigateToHome: () -> Unit) {
    composable(Screens.Authentication.route) {

        val oneTapSignInState = rememberOneTapSignInState()
        val authViewModel: AuthViewModel= viewModel()
        val snackBar = SnackbarHostState()

        AuthenticationScreen(
            isAuthenticated = authViewModel.isAuthenticated.collectAsState().value,
            oneTapSignInState = oneTapSignInState,
            loadingState = authViewModel.isLoading.collectAsState().value,
            onButtonClicked = {
                authViewModel.loading(true)
                oneTapSignInState.open()
                              },
            onSuccess = authViewModel::onSuccess,
            onError =  authViewModel::onError,
            navigateToHome = navigateToHome
        )
    }
}

