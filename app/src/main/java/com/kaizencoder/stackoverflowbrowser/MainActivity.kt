package com.kaizencoder.stackoverflowbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kaizencoder.stackoverflowbrowser.ui.Route
import com.kaizencoder.stackoverflowbrowser.ui.screens.QuestionDetailScreen
import com.kaizencoder.stackoverflowbrowser.ui.screens.QuestionListScreen
import com.kaizencoder.stackoverflowbrowser.ui.theme.StackOverflowBrowserTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StackOverflowBrowserTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(R.string.app_name)) }
                        )
                    },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = Route.ListScreen){
                        composable<Route.ListScreen> {
                            QuestionListScreen(onNavigateToDetailScreen = { id ->
                                navController.navigate(route = Route.DetailScreen(id))
                            },
                                Modifier.padding(innerPadding))
                        }
                        composable<Route.DetailScreen> { backStackEntry ->
                            val details: Route.DetailScreen = backStackEntry.toRoute()
                            QuestionDetailScreen(details.questionId, Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}
