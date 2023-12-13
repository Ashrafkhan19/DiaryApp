package com.everynation.church.diaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.everynation.church.diaryapp.navigation.Screens
import com.everynation.church.diaryapp.navigation.SetUpNavGraph
import com.everynation.church.diaryapp.ui.theme.DiaryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaryAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                SetUpNavGraph(startDestination = Screens.Authentication.route, navController = navController)
            }
        }
    }
}
