package com.example.lab_activity_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lab_activity_7.ui.theme.Labactivity7Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            Labactivity7Theme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Home
                ) {

                    composable<Home> {

                        HomeScreen(
                            onShowGreeting = { typedName ->

                                navController.navigate(
                                    Greeting(
                                        userName = typedName
                                    )
                                )
                            }
                        )
                    }

                    composable<Greeting> { backStackEntry ->

                        val greeting: Greeting =
                            backStackEntry.toRoute()

                        GreetingScreen(
                            userName = greeting.userName
                        )
                    }
                }
            }
        }
    }
}