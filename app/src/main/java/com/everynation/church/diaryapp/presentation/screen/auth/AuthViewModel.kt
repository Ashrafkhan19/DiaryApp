package com.everynation.church.diaryapp.presentation.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everynation.church.diaryapp.util.Constants
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    var isLoading = MutableStateFlow(false)
        private set

    var isAuthenticated = MutableStateFlow(false)
        private set

    init {
        viewModelScope.launch {
            App.create(Constants.APP_ID).emailPasswordAuth.registerUser("emirsarafolu058@gmail.com","emir")

        }

    }

    fun onSuccess(token: String) = viewModelScope.launch {
        isLoading.value = false
        isAuthenticated.value = App.create(Constants.APP_ID).login(Credentials.google(token, GoogleAuthType.ID_TOKEN)).loggedIn
    }

    fun onError(error: String){

    }

    fun loading(loading: Boolean) {
        isLoading.value = loading
    }

}