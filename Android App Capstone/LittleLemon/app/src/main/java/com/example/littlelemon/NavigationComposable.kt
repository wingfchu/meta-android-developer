package com.example.littlelemon

import android.content.Context

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.composables.Home
import com.example.littlelemon.composables.Onboarding
import com.example.littlelemon.composables.Profile

@Composable
fun NavigationComposable(context: Context, navController: NavHostController) {
    val isLoggedIn = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE).getBoolean("isLoggedIn", false)
    val startDestination = Onboarding.route
//    val startDestination = if (isLoggedIn) Home.route else Onboarding.route

    NavHost(navController = navController, startDestination = startDestination){
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }
        composable(Onboarding.route){
            Onboarding(navController)
        }
    }
}