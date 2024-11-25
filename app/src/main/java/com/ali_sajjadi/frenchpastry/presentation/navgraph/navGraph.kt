package com.ali_sajjadi.frenchpastry.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ali_sajjadi.frenchpastry.presentation.screen.Login
import com.ali_sajjadi.frenchpastry.presentation.screen.SplashScreen

@Composable
fun NavGraph( startDestination: String) {
     val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Route.SplashScreen.route){
            SplashScreen(
                navigateToLogin = {navController.navigate(Route.LoginScreen.route)}
            ){ }
        }
        composable(route = Route.LoginScreen.route){
            Login()
        }

    }



}