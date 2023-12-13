package com.everynation.church.diaryapp.navigation

sealed class Screens(val route: String) {
    data object Authentication: Screens(route = "authentication_screen")
    data object Home: Screens(route = "home_screen")
    data object Write: Screens(route = "write_screen?diaryId={diaryId}"){
        
    }
}