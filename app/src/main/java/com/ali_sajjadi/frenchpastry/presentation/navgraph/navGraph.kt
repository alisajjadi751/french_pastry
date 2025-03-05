package com.ali_sajjadi.frenchpastry.presentation.navgraph

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ali_sajjadi.frenchpastry.Test
import com.ali_sajjadi.frenchpastry.data.manager.TokenManager
import com.ali_sajjadi.frenchpastry.presentation.screen.cart.CartScreen
import com.ali_sajjadi.frenchpastry.presentation.screen.detailsProduct.DetailsScreen
import com.ali_sajjadi.frenchpastry.presentation.screen.home.HomeScreen
import com.ali_sajjadi.frenchpastry.presentation.screen.login.LoginScreen
import com.ali_sajjadi.frenchpastry.presentation.screen.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val activity = context as? Activity

    NavHost(navController = navController, startDestination = Route.SplashScreen.route) {
        composable(route = Route.SplashScreen.route) {
            SplashScreen(
                navigateToLogin = {
                    navController.navigate(Route.LoginScreen.route) {
                        popUpTo(Route.SplashScreen.route) { inclusive = true }
                    }
                },
                navigateToHome = {
                    navController.navigate(Route.HomeScreen.route) {
                        popUpTo(Route.SplashScreen.route) { inclusive = true }
                    }
                },
                exitApp = { activity?.finish() }
            )
        }
        composable(route = Route.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(
            route = Route.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            DetailsScreen(id = id ?: -1)
        }
        composable(route = Route.UserScreen.route) {
            Test()
        }
        composable(route = Route.CartScreen.route){
            CartScreen(navController)
        }
    }
}

